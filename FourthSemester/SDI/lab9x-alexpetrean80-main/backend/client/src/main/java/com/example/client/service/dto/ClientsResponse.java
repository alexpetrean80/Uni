package com.example.client.service.dto;

import com.example.client.domain.Client;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class ClientsResponse implements Serializable {
    private final Collection<ClientResponse> clients = new ArrayList<>();

    public void addClient(Client c) {
        clients.add(ClientResponse.builder()
                .id(c.getId())
                .address(c.getAddress())
                .name(c.getName())
                .phoneNumber(c.getPhoneNumber())
                .address(c.getAddress())
                .build());
    }
}
