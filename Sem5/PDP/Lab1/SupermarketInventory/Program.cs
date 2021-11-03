using System;
using System.Collections.Generic;
using System.Threading;

namespace SupermarketInventory {
    internal static class Program {
        private static Random _rnd = new Random();
        private static bool _ok = true;

        private static int GetRandomId() {
            return _rnd.Next(10);
        }

        private static int GetRandomQuantity() {
            return _rnd.Next(100);
        }

        private static void Main() {
            const int nrThreads = 5000;
            const int nrTrans = 100000;
            var initialProducts = new List<Product> {
                new() {
                    Id = 0,
                    Price = 5,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 1,
                    Price = 20,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 2,
                    Price = 2,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 3,
                    Price = 8,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 4,
                    Price = 21,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 5,
                    Price = 120,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 6,
                    Price = 80,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 7,
                    Price = 54,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 8,
                    Price = 41,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 9,
                    Price = 99,
                    Quantity = ulong.MaxValue
                },
                new() {
                    Id = 10,
                    Price = 20,
                    Quantity = ulong.MaxValue
                }
            };

            var service = new SupermarketService(initialProducts);


            var threads = new Thread[nrThreads];
            for (var i = 0; i < nrThreads; i++) {
                var i1 = i;
                threads[i] = new Thread(() => {
                    for (var j = 0; j < (nrTrans / nrThreads); j++) {
                        var pq = new Dictionary<uint, uint>();
                        for (var k = 0; k < _rnd.Next(11); k++) {
                            var key = (uint) _rnd.Next(11);
                            while (pq.ContainsKey(key)) {
                                key = (uint) _rnd.Next(11);
                            }

                            pq.Add(key, (uint) _rnd.Next(20));
                        }

                        service.MakeSale(pq);
                    }
                });
            }

            var startTime = DateTime.Now;
            
            foreach (var thread in threads) {
                thread.Start();
            }

            var statisticsThread = new Thread(() => {
                do {
                    Thread.Sleep(TimeSpan.FromSeconds(.1));
                    if (!service.CheckInventory()) {
                        Console.WriteLine("There is an issue with inventory!");
                    }
                } while (_ok);
            });

            statisticsThread.Start();

            foreach (var thread in threads) {
                thread.Join();
            }

            _ok = false;
            statisticsThread.Join();

            var spentTime = DateTime.Now - startTime;

            Console.WriteLine(
                $"{nrTrans} transactions on {nrThreads} threads done in {spentTime.Milliseconds} ms.");
        }
    }
}