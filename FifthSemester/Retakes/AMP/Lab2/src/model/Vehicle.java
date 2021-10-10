package model;

import java.util.Objects;

public abstract class Vehicle implements IVehicle {
    private final int id;
    private final VehicleType type;
    private final String colour;
    private final String brand;
    private final String model;
    private final int NumberOfWheels;

    protected Vehicle(int id, VehicleType type, String colour, String brand, String model, int numberOfWheels) {
        this.id = id;
        this.type = type;
        this.colour = colour;
        this.brand = brand;
        this.model = model;
        NumberOfWheels = numberOfWheels;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public VehicleType getType() {
        return type;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getNumberOfWheels() {
        return NumberOfWheels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return getId() == vehicle.getId()
                && getNumberOfWheels() == vehicle.getNumberOfWheels()
                && getType() == vehicle.getType()
                && Objects.equals(getColour(), vehicle.getColour())
                && Objects.equals(getBrand(), vehicle.getBrand()) && Objects.equals(getModel(), vehicle.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getColour(), getBrand(), getModel(), getNumberOfWheels());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type=" + type.toString().toLowerCase() +
                ", colour='" + colour + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", NumberOfWheels=" + NumberOfWheels +
                '}';
    }
}
