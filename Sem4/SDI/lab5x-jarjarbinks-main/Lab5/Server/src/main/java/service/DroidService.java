package service;

import domain.baseEntities.Droid;
import exceptions.DealershipException;
import exceptions.ExistingException;
import exceptions.NotFoundException;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DroidService {
    Repository<Long, Droid> repository;

    /**
     * @param repository
     */
    public DroidService(Repository<Long, Droid> repository) {
        this.repository = repository;
    }

    /**
     * @param newDroid
     * @throws ExistingException if the droid already exists
     */
    public void addDroid(Droid newDroid) throws ExistingException, ClassNotFoundException {
        var result = repository.save(newDroid);
        result.ifPresent(droid -> {
            throw new ExistingException("droid exists");
        });
    }

    /**
     * @return List<Droid>
     * returns all droids in the repo
     */
    public List<Droid> getDroids() throws ClassNotFoundException {
        var droids = new ArrayList<Droid>();
        repository.findAll().forEach(droids::add);
        return droids;
    }

    public void deleteDroid(Long id) throws NotFoundException, ClassNotFoundException {
        var result = repository.delete(id);
        result.ifPresentOrElse(droid -> {
        }, () -> {
            throw new NotFoundException("Droid does not exist.");
        });

    }


    /**
     * @param msg - model type we are filtering by
     * @return List<Droid> - the filtered list of droids
     */
    public List<Droid> filterDroidsByModel(String msg) throws ClassNotFoundException {
        var droids = new ArrayList<Droid>();
        repository.findAll().forEach(droids::add);
        return droids.stream()
                .filter(s -> s.getModel().contains(msg))
                .collect(Collectors.toList());
    }

    public List<Droid> filterDroidsByMinimumPowerUsage(int min) throws ClassNotFoundException {
        var droids = new ArrayList<Droid>();
        this.repository.findAll().forEach(droids::add);
        return droids.stream()
                .filter(d -> d.getPowerUsage() > min)
                .collect(Collectors.toList());
    }

    public void updateDroid(Droid newDroid) throws ClassNotFoundException {
        var result = this.repository.update(newDroid);
        result.ifPresent(d -> {
            throw new DealershipException("update failed");
        });
    }

    public List<Droid> filterDroidsById(String id) throws ClassNotFoundException {
        var droids = new ArrayList<Droid>();
        repository.findAll().forEach(droids::add);
        return droids.stream()
                .filter(s -> s.getId().toString().contains(id))
                .collect(Collectors.toList());
    }
}

