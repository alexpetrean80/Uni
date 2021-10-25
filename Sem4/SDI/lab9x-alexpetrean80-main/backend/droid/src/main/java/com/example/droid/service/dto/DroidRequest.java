package com.example.droid.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class DroidRequest implements Serializable {
    private final double price;
    private final int batteryTime;
    private final String model;
    private final boolean driver;
}
