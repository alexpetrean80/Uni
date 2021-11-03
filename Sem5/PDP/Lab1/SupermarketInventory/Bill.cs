using System.Collections.Generic;

namespace SupermarketInventory {
    public class Bill {
        public uint Id { get; init; }
        public double TotalPrice { get; init; }
        public Dictionary<uint, uint> ProductQuantity { get; init; }
    }
}