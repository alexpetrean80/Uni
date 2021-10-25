package controller;

import model.IVehicle;
import model.VehicleType;

public interface IController {
    void addVehicle(VehicleType type, String colour, String brand, String model);

    void deleteVehicle(int id);

    IVehicle[] getAllRedVehicles();
}
