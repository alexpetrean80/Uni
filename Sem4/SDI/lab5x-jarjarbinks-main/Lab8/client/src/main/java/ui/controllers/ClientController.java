package ui.controllers;

import domain.baseEntities.Client;
import dto.ClientDto;
import dto.CollectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class ClientController {

    @Autowired
    ExecutorService executorService;

    @Autowired
    RestTemplate template;

    public CompletableFuture<String> updateClient(Long id,String name,String address,String phoneNumber){
        return CompletableFuture.supplyAsync(()-> {
            var dto = new ClientDto(name,address,phoneNumber);
            template.put("http://localhost:4000/client/{id}",dto,id);
            return "Client updated. ";},executorService);
    }

    public CompletableFuture<String> addClient(String name,String address,String phoneNumber){
        return CompletableFuture.supplyAsync(()->{
            var dto = new ClientDto(name,address,phoneNumber);
            template.postForObject("http://localhost:4000/client",dto,ClientDto.class);
            return "Client added.";
                },executorService);
    }

    public CompletableFuture<String> deleteClient(Long id){
        return CompletableFuture.supplyAsync(()->{
            template.delete("http://localhost:4000/client/{id}",id);
            return "Client deleted.";},executorService);
    }

    public CompletableFuture<Iterable<Client>> filterClientsByName(String name){
        return CompletableFuture.supplyAsync(()->{
            CollectionDto<Long,ClientDto> dto = template.getForObject("http://localhost:4000/clients/{name}",CollectionDto.class,name);
            return dto.getElements().stream().map(d ->{
                var client = new Client(d.getName(),d.getAddress(),d.getPhoneNumber());
                client.setId(d.getId());
                return client;
            }).collect(Collectors.toList());},executorService);
    }

    public CompletableFuture<Iterable<Client>> filterClientsByAddress(String addr){
        return CompletableFuture.supplyAsync(()->{
            CollectionDto<Long,ClientDto> dto = template.getForObject("http://localhost:4000/clients/{addr}",CollectionDto.class,addr);
            return dto.getElements().stream().map(d ->{
                var client = new Client(d.getName(),d.getAddress(),d.getPhoneNumber());
                client.setId(d.getId());
                return client;
            }).collect(Collectors.toList());},executorService);
    }

    public CompletableFuture<Iterable<Client>> filterClientsById(Long id){
        return CompletableFuture.supplyAsync(()->{
            CollectionDto<Long,ClientDto> dto = template.getForObject("http://localhost:4000/clients/{id}",CollectionDto.class,id);
            return dto.getElements().stream().map(d ->{
                var client = new Client(d.getName(),d.getAddress(),d.getPhoneNumber());
                client.setId(d.getId());
                return client;
            }).collect(Collectors.toList());},executorService);
    }

    public CompletableFuture<Iterable<Client>> getAllClients() {
        return CompletableFuture.supplyAsync(() -> {
            CollectionDto<Long, ClientDto> dto = template.getForObject("http://localhost:4000/clients", CollectionDto.class);
            return dto.getElements().stream().map(d -> {
                var client = new Client(d.getName(), d.getAddress(), d.getPhoneNumber());
                client.setId(d.getId());
                return client;
            }).collect(Collectors.toList());
        }, executorService);
    }
}
