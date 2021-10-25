package model;

public class Car extends Vehicle {
    public Car(int id, String colour, String brand, String model) {
        super(id, VehicleType.CAR, colour, brand, model, 4);
    }
}
