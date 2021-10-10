package repository;

import model.IVehicle;

import java.util.Arrays;

public class Repository implements IRepository {
    private IVehicle[] vehicles = new IVehicle[100];
    private int vehicleCount = 0;

    @Override
    public void Add(IVehicle v) {
        if (isVehicleInParkingLot(v.getId())) {
            throw new RuntimeException("Vehicle is already in the parking lot.");
        }

        if (vehicleCount == 100) {
            throw new RuntimeException("Parking lot is full.");
        }
        vehicles[vehicleCount++] = v;
    }

    @Override
    public void Delete(int id) {
        if (!isVehicleInParkingLot(id)) {
            throw new RuntimeException("Vehicle is not in the parking lot.");
        }

        vehicles = (IVehicle[]) Arrays.stream(vehicles)
                .filter(v -> v.getId() != id).toArray();
    }

    private boolean isVehicleInParkingLot(int id) {
        return Arrays.stream(vehicles)
                .anyMatch(v -> v.getId() == id);

    }

    @Override
    public IVehicle[] GetAll() {
        return this.vehicles;
    }
}
