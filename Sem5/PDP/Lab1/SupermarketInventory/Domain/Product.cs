namespace SupermarketInventory.Domain {
    public class Product {
        public uint Id { get; init; }
        public double Price { get; init; }
        public uint Quantity { get; set; }
    }
}