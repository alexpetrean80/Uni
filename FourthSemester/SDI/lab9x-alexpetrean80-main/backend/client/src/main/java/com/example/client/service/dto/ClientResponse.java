package com.example.client.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ClientResponse implements Serializable {
    private final String id;
    private final String name;
    private final String address;
    private final String phoneNumber;
}
