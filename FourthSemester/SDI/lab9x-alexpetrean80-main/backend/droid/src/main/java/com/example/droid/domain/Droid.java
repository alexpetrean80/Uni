package com.example.droid.domain;


import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class Droid implements Serializable {
    @Id
    private String id;
    private double price;
    private int batteryTime;
    private String model;
    private boolean driver;
}
