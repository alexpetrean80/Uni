package service.interfaces;

import domain.baseEntities.Droid;

import java.util.List;

public interface DroidService {

    void addDroid(double powerUsage, double price, int batteryTime, String model, boolean driver);

    void deleteDroid(Long id);

    List<Droid> getAllDroids();

    List<Droid> getDroidsByMinimumPowerUsage(int filter);

    List<Droid> getDroidsById(Long id);

    List<Droid> getDroidsByModel(String model);

    void updateDroid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver);

}
