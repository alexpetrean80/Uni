package model;

public interface IVehicle {
    int getId();

    VehicleType getType();

    String getColour();

    int getNumberOfWheels();

    String getBrand();

    String getModel();

    String toString();
}
