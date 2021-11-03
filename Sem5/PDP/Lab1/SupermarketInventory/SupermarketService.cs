using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;

namespace SupermarketInventory {
    public class SupermarketService {
        private static double _money;
        private readonly Mutex _mtx = new();

        private readonly Dictionary<uint, ulong> _initialStock;

        private readonly List<Product> _products;

        private readonly List<Bill> _bills = new();

        public SupermarketService(List<Product> products) {
            _products = products;
            _initialStock = ComputeCurrentStock();
        }

        public void MakeSale(Dictionary<uint, uint> productQuantities) {
            _mtx.WaitOne();

            var totalPrice = 0d;
            foreach (var (productId, quantity) in productQuantities) {
                var p = _products.FirstOrDefault(p => p.Id == productId) ??
                        throw new Exception($"Product with ID {productId} does not exist.");

                if (p.Quantity == 0 || p.Quantity < quantity) {
                    throw new Exception($"Product with ID {p.Id} does not exist.");
                }

                p.Quantity -= quantity;
                var cost = quantity * p.Price;
                _money += cost;

                totalPrice += cost;
            }

            _bills.Add(new Bill {
                Id = (uint) _bills.Count,
                TotalPrice = totalPrice,
                ProductQuantity = productQuantities
            });

            _mtx.ReleaseMutex();
        }

        public bool CheckInventory() {
            _mtx.WaitOne();
            var res = CheckFinance() && CheckStock();
            _mtx.ReleaseMutex();
            return res;
        }

        private bool CheckFinance() {
            var totalFromBills = _bills.Aggregate(0d, (total, b) => total + b.TotalPrice);
            return Math.Abs(totalFromBills - _money) == 0d;
        }

        private bool CheckStock() {
            var currentStock = ComputeCurrentStock();
            var spentStock = ComputeSpentStock();

            foreach (var (productId, initialQuantity) in _initialStock) {
                if (spentStock[productId] + currentStock[productId] != initialQuantity) {
                    return false;
                }
            }

            return true;
        }

        private Dictionary<uint, ulong> ComputeCurrentStock() {
            var currentStock = new Dictionary<uint, ulong>();
            _products.ForEach(p => { currentStock.Add(p.Id, p.Quantity); });
            return currentStock;
        }

        private Dictionary<uint, ulong> ComputeSpentStock() {
            var spentStock = new Dictionary<uint, ulong>();
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
    }
}