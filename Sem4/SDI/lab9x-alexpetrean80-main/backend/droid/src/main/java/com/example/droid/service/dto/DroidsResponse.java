package com.example.droid.service.dto;

import com.example.droid.domain.Droid;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class DroidsResponse implements Serializable {
    private final Collection<DroidResponse> droids = new ArrayList<>();

    public void addDroid(Droid d) {
        droids.add(DroidResponse.builder()
                .id(d.getId())
                .batteryTime(d.getBatteryTime())
                .driver(d.isDriver())
                .model(d.getModel())
                .price(d.getPrice())
                .build());
    }
}
