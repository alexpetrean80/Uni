using System;
using System.Collections.Generic;
using System.Threading;
using SupermarketInventory.Domain;

namespace SupermarketInventory {
    internal static class Program {
        private static Random _rnd = new Random();

        static int GetRandomId() {
            return _rnd.Next(10);
        }

        static int GetRandomQuantity() {
            return _rnd.Next(100);
        }

        private static void Main() {
            var initialProducts = new List<Product> {
                new() {
                    Id = 0,
                    Price = 5,
                    Quantity = 10000
                },
                new() {
                    Id = 1,
                    Price = 20,
                    Quantity = 10000
                },
                new() {
                    Id = 2,
                    Price = 2,
                    Quantity = 10000
                },
                new() {
                    Id = 3,
                    Price = 8,
                    Quantity = 10000
                },
                new() {
                    Id = 4,
                    Price = 21,
                    Quantity = 10000
                },
                new() {
                    Id = 5,
                    Price = 120,
                    Quantity = 10000
                },
                new() {
                    Id = 6,
                    Price = 80,
                    Quantity = 10000
                },
                new() {
                    Id = 7,
                    Price = 54,
                    Quantity = 10000
                },
                new() {
                    Id = 8,
                    Price = 41,
                    Quantity = 10000
                },
                new() {
                    Id = 9,
                    Price = 99,
                    Quantity = 10000
                },
                new() {
                    Id = 10,
                    Price = 0,
                    Quantity = 10000
                }
            };

            var service = new SupermarketService(initialProducts);

            var view = new SupermarketView(service);

            var viewThread = new Thread(() => { view.Run(); });

            var statisticsThread = new Thread(() => {
                while (true) {
                    Thread.Sleep(TimeSpan.FromSeconds(10));
                    Console.WriteLine("Periodic inventory check:" + (service.CheckInventory()
                        ? "Inventory is ok!"
                        : "There is an issue with inventory!"));
                }
            });

            viewThread.Start();
            statisticsThread.Start();

            viewThread.Join();
            statisticsThread.Join();
        }
    }
}