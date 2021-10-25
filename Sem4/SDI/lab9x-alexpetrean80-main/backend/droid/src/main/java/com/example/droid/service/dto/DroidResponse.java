package com.example.droid.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
public class DroidResponse implements Serializable {
    private final String id;
    private final double price;
    private final int batteryTime;
    private final String model;
    private final boolean driver;
}
