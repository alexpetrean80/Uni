using System;
using System.Threading;

namespace Lab2 {
    internal static class Program {
        private static void Main() {
            var v1 = new int[3] {1, 2, 3};
            var v2 = new int[3] {5, 6, 7};

            var wh = new ManualResetEvent(false);

            var prod = 0;
            var producer = new Thread(() => {
                for (var i = 0; i < 3; i++) {
                    prod = v1[i] * v2[i];
                    Console.WriteLine($"product: {prod}");
                    wh.Set();
                    Thread.Sleep(5);
                }
            });

            var sum = 0;

            var consumer = new Thread(() => {
                for (var i = 0; i < 3; i++) {
                    wh.WaitOne();
                    sum += prod;
                    Console.WriteLine($"partial sum: {sum}");
                    wh.Reset();
                }
            });

            producer.Start();
            consumer.Start();

            producer.Join();
            consumer.Join();


            Console.WriteLine($"\nfinal sum: {sum}");
        }
    }
}