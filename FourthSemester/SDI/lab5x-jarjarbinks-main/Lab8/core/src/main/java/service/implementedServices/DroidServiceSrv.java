package service.implementedServices;

import domain.baseEntities.Droid;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.*;
import service.interfaces.DroidService;

import static java.lang.Math.max;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DroidServiceSrv implements DroidService {

    private final IDroidRepo repository;

    public DroidServiceSrv(IDroidRepo repository) {
        this.repository = repository;
    }


    @Override
    public void addDroid(double powerUsage, double price, int batteryTime, String model, boolean driver) {
        long id = 0;
        for (Droid d : this.repository.findAll())
            id = max(id, d.getId() + 1);
        var newDroid = new Droid(id, powerUsage, price, batteryTime, model, driver);
        repository.save(newDroid);
    }

    @Override
    public void deleteDroid(Long id) throws NotFoundException {
        repository.findById(id)
                .ifPresentOrElse((droid) -> repository.deleteById(droid.getId()),
                        () -> {
                            throw new NotFoundException("Droid does not exist!");
                        });
    }

    @Override
    public List<Droid> getAllDroids() {
        return repository.findAll();
    }

    @Override
    public List<Droid> getDroidsByMinimumPowerUsage(int min) {
        return repository.findAll()
                .stream()
                .filter(d -> d.getPowerUsage() > min)
                .collect(Collectors.toList());

    }

    @Override
    public List<Droid> getDroidsById(Long id) {
        return repository.findAll()
                .stream()
                .filter(s -> Objects.equals(s.getId(), id))
                .collect(Collectors.toList());

    }

    @Override
    public List<Droid> getDroidsByModel(String model) {
        return repository.findAll()
                .stream()
                .filter(s -> s.getModel().contains(model))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void updateDroid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver) throws NotFoundException {
        repository.findById(id)
                .ifPresentOrElse((droid) -> {
                            droid.setPowerUsage(powerUsage);
                            droid.setPrice(price);
                            droid.setBatteryTime(batteryTime);
                            droid.setModel(model);
                            droid.setDriver(driver);
                        },
                        () -> {
                            throw new NotFoundException("Droid does not exist");
                        }
                );
    }
}
