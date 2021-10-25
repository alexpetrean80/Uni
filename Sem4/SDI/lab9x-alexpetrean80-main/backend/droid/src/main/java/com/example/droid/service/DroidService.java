package com.example.droid.service;

import com.example.droid.domain.Droid;
import com.example.droid.repo.DroidRepository;
import com.example.droid.service.dto.DroidRequest;
import com.example.droid.service.dto.DroidResponse;
import com.example.droid.service.dto.DroidsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroidService {
    @Autowired
    private DroidRepository repo;

    public DroidResponse addDroid(DroidRequest data) {
        var droid = new Droid();
        droid.setDriver(data.isDriver());
        droid.setBatteryTime(data.getBatteryTime());
        droid.setPrice(data.getPrice());
        droid.setModel(data.getModel());

        var res = repo.save(droid);

        return DroidResponse.builder()
                .id(res.getId())
                .driver(res.isDriver())
                .batteryTime(res.getBatteryTime())
                .model(res.getModel())
                .price(res.getPrice())
                .build();
    }

    public void deleteDroid(String id) {
        repo.deleteById(id);
    }

    public DroidResponse updateDroid(String id, DroidRequest data) {
        var droid = new Droid();
        droid.setDriver(data.isDriver());
        droid.setBatteryTime(data.getBatteryTime());
        droid.setPrice(data.getPrice());
        droid.setModel(data.getModel());

        var res = repo.save(droid);

        return DroidResponse.builder()
                .id(res.getId())
                .driver(res.isDriver())
                .batteryTime(res.getBatteryTime())
                .model(res.getModel())
                .price(res.getPrice())
                .build();
    }

    public DroidResponse getDroidById(String id) {
        var res = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Droid not found"));

        return DroidResponse.builder()
                .id(res.getId())
                .driver(res.isDriver())
                .batteryTime(res.getBatteryTime())
                .model(res.getModel())
                .price(res.getPrice())
                .build();
    }

    public DroidsResponse getAllDroids() {
        var res = new DroidsResponse();
        repo.findAll()
                .forEach(res::addDroid);

        return res;
    }

    public DroidsResponse getDroidsByModel(String model) {
        var res = new DroidsResponse();
        repo.findAll()
                .stream()
                .filter((d) -> d.getModel().equals(model))
                .forEach(res::addDroid);
        return res;
    }
}

