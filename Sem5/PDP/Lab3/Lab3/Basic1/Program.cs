using System;
using System.Collections.Generic;
using System.Linq;
using Lib;

namespace Basic1
{
    internal static class Program
    {
        private static readonly Matrix M1 = new(new List<List<int>>
        {
            new() {1, 2, 3},
            new() {4, 5, 6},
            new() {7, 8, 9}
        });

        private static readonly Matrix M2 = new(new List<List<int>>
        {
            new() {9, 8, 7},
            new() {6, 5, 4},
            new() {3, 2, 1}
        });

        private static int ComputeFirstElement()
        {
            Console.Write(M1.GetLine(0).ToString());
            return M1.GetLine(0).Aggregate(1, (acc, x) => acc * x) +
                   M2.GetColumn(0).Aggregate(1, (acc, x) => acc * x);
        }

        private static void Main(string[] args)
        {
            Console.WriteLine($"first element: {ComputeFirstElement()}");
        }
    }
}