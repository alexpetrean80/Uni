using System;
using System.Collections.Generic;
using SupermarketInventory.Domain;

namespace SupermarketInventory {
    internal static class Program {
        private static void Main() {
            var initialProducts = new List<Product>();

            var service = new SupermarketService(initialProducts);
            
            Console.WriteLine("Hello World!");
        }
    }
}