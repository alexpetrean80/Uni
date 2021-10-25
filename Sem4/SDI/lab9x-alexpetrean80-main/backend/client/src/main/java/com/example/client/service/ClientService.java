package com.example.client.service;

import com.example.client.domain.Client;
import com.example.client.repo.ClientRepository;
import com.example.client.service.dto.ClientRequest;
import com.example.client.service.dto.ClientResponse;
import com.example.client.service.dto.ClientsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repo;

    public ClientResponse addClient(ClientRequest data) {
        var client = new Client();
        client.setAddress(data.getAddress());
        client.setName(data.getName());
        client.setPhoneNumber(data.getPhoneNumber());

        var res = repo.save(client);

        return ClientResponse.builder()
                .id(res.getId())
                .address(res.getAddress())
                .name(res.getName())
                .phoneNumber(res.getPhoneNumber())
                .build();
    }

    public void deleteClient(String id) {
        repo.deleteById(id);
        (new RestTemplateBuilder()).build()
                .delete("http://localhost:4002/purchase/client/" + id);
    }

    public ClientResponse updateClient(String id, ClientRequest data) {
        var client = new Client();
        client.setAddress(data.getAddress());
        client.setName(data.getName());
        client.setPhoneNumber(data.getPhoneNumber());
        client.setId(id);

        var res = repo.save(client);

        return ClientResponse.builder()
                .id(res.getId())
                .address(res.getAddress())
                .name(res.getName())
                .phoneNumber(res.getPhoneNumber())
                .build();
    }

    public ClientResponse getClientById(String id) {
        var client = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client doesn't exist."));

        return ClientResponse.builder()
                .id(client.getId())
                .phoneNumber(client.getPhoneNumber())
                .name(client.getName())
                .address(client.getAddress())
                .build();
    }

    public ClientsResponse getAllClients() {
        var res = new ClientsResponse();

        repo.findAll()
                .forEach(res::addClient);
        return res;
    }

    public ClientsResponse getClientsByAddress(String addr) {
        var res = new ClientsResponse();

        repo.findAll()
                .stream()
                .filter(c -> c.getAddress().equals(addr))
                .forEach(res::addClient);

        return res;
    }
}
