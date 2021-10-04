package com.example.client.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class Client implements Serializable {
    @Id
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
}
