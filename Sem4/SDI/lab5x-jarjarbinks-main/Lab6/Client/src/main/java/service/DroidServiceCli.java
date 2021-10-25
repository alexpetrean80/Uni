package service;

import org.springframework.beans.factory.annotation.Autowired;
import domain.baseEntities.Droid;

import java.util.List;

public class DroidServiceCli implements DroidService {
    @Autowired
    DroidService droidService;

    @Override
    public String addDroid(double powerUsage, double price, int batteryTime, String model, boolean driver) {
        return droidService.addDroid(powerUsage, price, batteryTime, model, driver);
    }

    @Override
    public String deleteDroid(Long id) {
        return droidService.deleteDroid(id);
    }

    @Override
    public List<Droid> getAllDroids() {
        return droidService.getAllDroids();
    }

    @Override
    public List<Droid> getDroidsByMinimumPowerUsage(int min) {
        return droidService.getDroidsByMinimumPowerUsage(min);
    }

    @Override
    public List<Droid> getDroidsById(Long id) {
        return droidService.getDroidsById(id);
    }

    @Override
    public List<Droid> getDroidsByModel(String model) {
        return droidService.getDroidsByModel(model);
    }

    @Override
    public String updateDroid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver) {
        return droidService.updateDroid(id, powerUsage, price, batteryTime, model, driver);
    }
}