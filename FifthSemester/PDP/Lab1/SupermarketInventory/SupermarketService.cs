using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using SupermarketInventory.Domain;

namespace SupermarketInventory {
    public class SupermarketService {
        private static double _money;
        private readonly Mutex _moneyMutex = new();
        private static uint _billCount;
        private readonly Mutex _billCountMutex = new();
        private readonly Dictionary<uint, uint> _initialStock;
        private readonly Mutex _productsMutex = new();
        private readonly List<Product> _products;
        private readonly List<Bill> _bills = new();
        private readonly Mutex _billMutex = new();

        public SupermarketService(List<Product> products) {
            _products = products;
            _initialStock = ComputeCurrentStock();
        }

        public void MakeSale(Dictionary<uint, uint> productQuantities) {
            var totalPrice = 0d;
            foreach (var (productId, quantity) in productQuantities) {
                totalPrice += SellProduct(productId, quantity);
            }

            _billMutex.WaitOne();
            _bills.Add(new Bill {
                Id = _billCount,
                TotalPrice = totalPrice,
                ProductQuantity = productQuantities
            });
            _billMutex.ReleaseMutex();
            _billCountMutex.WaitOne();
            _billCount++;
            _billCountMutex.ReleaseMutex();
        }

        public bool CheckInventory() {
            return CheckFinance() && CheckStock();
        }

        private bool CheckFinance() {
            var totalFromBills = _bills.Aggregate(0d, (total, b) => total + b.TotalPrice);
            return Math.Abs(totalFromBills - _money) == 0;
        }

        private bool CheckStock() {
            Dictionary<uint, uint> currentStock = null;
            Dictionary<uint, uint> spentStock = null;
            var currentStockTh = new Thread(() => { currentStock = ComputeCurrentStock(); });
            var spentStockTh = new Thread(() => { spentStock = ComputeSpentStock(); });
            currentStockTh.Start();
            spentStockTh.Start();

            currentStockTh.Join();
            spentStockTh.Join();

            foreach (var (productId, initialQuantity) in _initialStock) {
                if (spentStock[productId] + currentStock[productId] != initialQuantity) {
                    return false;
                }
            }

            return true;
        }

        private Dictionary<uint, uint> ComputeCurrentStock() {
            var currentStock = new Dictionary<uint, uint>();
            _products.ForEach(p => { currentStock.Add(p.Id, p.Quantity); });
            return currentStock;
        }

        private Dictionary<uint, uint> ComputeSpentStock() {
            var spentStock = new Dictionary<uint, uint>();
            _bills.ForEach(b => {
                foreach (var (productId, quantity) in b.ProductQuantity) {
                    if (spentStock.ContainsKey(productId)) {
                        spentStock[productId] += quantity;
                        continue;
                    }

                    spentStock.Add(productId, quantity);
                }
            });
            return spentStock;
        }


        private double SellProduct(uint productId, uint quantity) {
            var p = FindProduct(productId);

            if (p.Quantity == 0 || p.Quantity < quantity) {
                throw new Exception($"Product with ID {p.Id} does not exist.");
            }

            _productsMutex.WaitOne();
            p.Quantity -= quantity;
            _productsMutex.ReleaseMutex();

            var totalPrice = quantity * p.Price;

            _moneyMutex.WaitOne();
            _money += totalPrice;
            _moneyMutex.ReleaseMutex();

            return totalPrice;
        }

        private Product FindProduct(uint productId) {
            var p = _products.FirstOrDefault(p => p.Id == productId) ??
                    throw new Exception($"Product with ID {productId} does not exist.");

            return p;
        }
    }
}