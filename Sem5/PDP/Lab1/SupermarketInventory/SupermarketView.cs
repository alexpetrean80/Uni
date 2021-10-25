using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Globalization;
using SupermarketInventory.Domain;

namespace SupermarketInventory {
    public class SupermarketView {
        private SupermarketService _ss;

        public SupermarketView(SupermarketService ss) {
            _ss = ss;
        }

        public void Run() {
            PrintHello();

            while (true) {
                PrintMenu();
                try {
                    switch (GetUserOption()) {
                        case MenuOptions.Exit:
                            Environment.Exit(0);
                            break;
                        case MenuOptions.Purchase:
                            MakePurchase();
                            break;
                        default:
                            throw new ArgumentOutOfRangeException();
                    }
                }
                catch (Exception e) {
                    Console.WriteLine(e.Message);
                }
            }
        }

        private void MakePurchase() {
            var products = new Dictionary<uint, uint>();
            _ss.MakeSale(products);
        }

        private static MenuOptions GetUserOption() {
            return Console.ReadLine() switch {
                "0" => MenuOptions.Exit,
                "1" => MenuOptions.Purchase,
                _ => throw new Exception("Wrong option.")
            };
        }

        private void PrintMenu() {
            Console.WriteLine("-- Menu --");
            Console.WriteLine();
            Console.WriteLine("1. Make purchase.");
            Console.WriteLine();
            Console.WriteLine("0. Exit.");
        }

        private static void PrintHello() {
            Console.WriteLine("Welcome!");
        }
    }
}