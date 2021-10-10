package model;

public class Bicycle extends Vehicle {
    public Bicycle(int id, String colour, String brand, String model) {
        super(id, VehicleType.BICYCLE, colour, brand, model, 2);
    }
}
