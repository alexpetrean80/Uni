package service;

import domain.baseEntities.Droid;

import java.util.List;

public interface DroidService {

    String addDroid(double powerUsage, double price, int batteryTime, String model, boolean driver);

    String deleteDroid(Long id);

    List<Droid> getAllDroids();

    List<Droid> getDroidsByMinimumPowerUsage(int filter);

    List<Droid> getDroidsById(Long id);

    List<Droid> getDroidsByModel(String model);

    String updateDroid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver);

}
