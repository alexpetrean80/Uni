package service;

import domain.baseEntities.Droid;
import exceptions.DealershipException;
import exceptions.ExistingDroidException;
import exceptions.InexistentDroidException;
import repo.IRepository;

import javax.management.DescriptorAccess;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class Service {
    IRepository<Long, Droid> repository;

    /**
     * @param repository
     */
    public Service(IRepository<Long, Droid> repository) {
        this.repository = repository;
    }

    /**
     * @param newDroid
     * @throws ExistingDroidException if the droid already exists
     */
    public void addDroid(Droid newDroid) throws ExistingDroidException {
        var result = repository.save(newDroid);
        result.ifPresent(droid -> {
            throw new ExistingDroidException("droid exists");
        });
    }

    /**
     * @return List<Droid>
     * returns all droids in the repo
     */
    public List<Droid> getDroids() {
        var droids = new ArrayList<Droid>();
        repository.findAll().forEach(droids::add);
        return droids;
    }

    public void deleteDroid(Long id) throws InexistentDroidException {
        var result = repository.delete(id);
        result.ifPresentOrElse(droid -> {
        }, () -> {
            throw new InexistentDroidException("Droid does not exist.");
        });

    }


    /**
     *
     * @param msg - model type we are filtering by
     * @return List<Droid> - the filtered list of droids
     */
    public List<Droid> filterDroidsByModel(String msg) {
        var droids = new ArrayList<Droid>();
        repository.findAll().forEach(droids::add);
        return droids.stream()
                .filter(s -> s.getModel().contains(msg))
                .collect(Collectors.toList());
    }

    public List<Droid> filterDroidsByMinimumPowerUsage(int min){
        var droids = new ArrayList<Droid>();
        this.repository.findAll().forEach(droids::add);
        return droids.stream()
                .filter(d -> d.getPowerUsage() > min)
                .collect(Collectors.toList());
    }

    public void updateDroid(Droid newDroid) {
        var result = this.repository.update(newDroid);
        result.ifPresent(d -> {
            throw new DealershipException("update failed");
        });
    }

    public List<Droid> filterDroidsById(String id) {
        var droids = new ArrayList<Droid>();
        repository.findAll().forEach(droids::add);
        return droids.stream()
                .filter(s -> s.getId().toString().contains(id))
                .collect(Collectors.toList());
    }
}
