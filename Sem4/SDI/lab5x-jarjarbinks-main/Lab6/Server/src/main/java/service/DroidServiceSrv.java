package service;

import domain.baseEntities.Droid;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import repository.JPASpringRepo.*;
import static java.lang.Math.max;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class DroidServiceSrv implements DroidService {

    @Autowired
    private IDroidRepo repository;


    @Override
    public String addDroid(double powerUsage, double price, int batteryTime, String model, boolean driver) {


        long id = 0;
        for(Droid d : this.repository.findAll())
            id = max(id, d.getId() + 1);
        var newDroid = new Droid(id,powerUsage, price, batteryTime, model, driver);
        repository.save(newDroid);
        AtomicReference<String> resMsg = new AtomicReference<>("Droid saved successfully.");
        return resMsg.get();
    }

    @Override
    public String deleteDroid(Long id) {
        repository.findById(id)
                .ifPresentOrElse((droid) -> repository.deleteById(droid.getId()),
                        () -> {
                    throw new NotFoundException("Droid does not exist!");
                        }
                        );
        AtomicReference<String> resMsg = new AtomicReference<>("Droid deleted successfully.");
        return resMsg.get();
    }

    @Override
    public List<Droid> getAllDroids() {
        var droids = repository.findAll();
        return droids;
    }

    @Override
    public List<Droid> getDroidsByMinimumPowerUsage(int min) {
        var droids = new ArrayList<Droid>();
        try {
            repository.findAll().forEach(droids::add);
            return droids.stream()
                    .filter(d -> d.getPowerUsage() > min)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public List<Droid> getDroidsById(Long id) {
        var droids = new ArrayList<Droid>();
        try {
            repository.findAll().forEach(droids::add);
            return droids.stream()
                    .filter(s -> Objects.equals(s.getId(), id))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public List<Droid> getDroidsByModel(String model) {
        var droids = new ArrayList<Droid>();
        try {
            repository.findAll().forEach(droids::add);
            return droids.stream()
                    .filter(s -> s.getModel().contains(model))
                    .collect(Collectors.toList());
        } catch (NotFoundException e) {

            e.printStackTrace();
            return new ArrayList<>();
        }

    }



    @Override
    @Transactional
    public String updateDroid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver) {
        AtomicReference<String> resMsg = new AtomicReference<>("Droid updated successfully.");
        repository.findById(id)
                .ifPresentOrElse((droid) -> {
                    droid.setPowerUsage(powerUsage);
                    droid.setPrice(price);
                    droid.setBatteryTime(batteryTime);
                    droid.setModel(model);
                    droid.setDriver(driver);
                },
                        ()->{
                    throw new NotFoundException("Droid does not exist");
                        }
                );
        return resMsg.get();
    }
}
