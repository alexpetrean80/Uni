package controller;

import model.*;
import repository.IRepository;

import java.util.Arrays;

public class Controller implements IController {
    private final IRepository repo;
    int id = 0;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    @Override
    public void addVehicle(VehicleType type, String colour, String brand, String model) {
        var v = switch (type) {
            case CAR -> new Car(id, colour, brand, model);
            case BICYCLE -> new Bicycle(id, colour, brand, model);
            case MOTORCYCLE -> new Motorcycle(id, colour, brand, model);
        };
        System.out.println(v);
        repo.Add(v);
        id++;
    }

    @Override
    public void deleteVehicle(int id) {
        repo.Delete(id);
    }

    @Override
    public IVehicle[] getAllRedVehicles() {
        return (IVehicle[]) Arrays.stream(repo.GetAll())
                .filter(v -> v.getColour().equalsIgnoreCase("red")).toArray();
    }
}
