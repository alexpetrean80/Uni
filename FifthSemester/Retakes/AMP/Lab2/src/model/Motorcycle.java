package model;

public class Motorcycle extends Vehicle {
    public Motorcycle(int id, String colour, String brand, String model) {
        super(id, VehicleType.MOTORCYCLE, colour, brand, model, 2);
    }
}
