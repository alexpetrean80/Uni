package ui.controllers;


import domain.Keys.ClientDroidPrimaryKey;
import domain.baseEntities.Client;
import domain.baseEntities.Droid;
import domain.baseEntities.Purchase;
import dto.ClientDto;
import dto.CollectionDto;
import dto.DroidDto;
import dto.PurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class PurchaseController {

    @Autowired
    ExecutorService executorService;

    @Autowired
    RestTemplate template;

    public CompletableFuture<String> addPurchase(Long clientId, Long droidId, double totalPrice){
        return CompletableFuture.supplyAsync(()-> {
            CollectionDto<Long, DroidDto> dto = template.getForObject("http://localhost:4000/droids/{id}", CollectionDto.class,droidId);
            var dr= dto.getElements().stream().map(d -> {
                var droid = new Droid(d.getPowerUsage(), d.getPrice(), d.getBatteryTime(),d.getModel(),d.isDriver());
                droid.setId(d.getId());
                return droid;
            }).collect(Collectors.toList()).get(0);

            CollectionDto<Long,ClientDto> dto1 = template.getForObject("http://localhost:4000/clients/{id}",CollectionDto.class,clientId);
            var cl = dto1.getElements().stream().map(d ->{
                var client = new Client(d.getName(),d.getAddress(),d.getPhoneNumber());
                client.setId(d.getId());
                return client;
            }).collect(Collectors.toList()).get(0);

            var dto2 = new PurchaseDto(cl,dr,totalPrice);
            template.postForObject("http://localhost:4000/purchase",dto2,PurchaseDto.class);
            return "Purchase added.";},executorService);
    }

    public CompletableFuture<String> deletePurchase(Long id){
        return CompletableFuture.supplyAsync(()->{
            template.delete("http://localhost:4000/purchase/{id}",id);
            return "Droid deleted.";},executorService);
    }

    public CompletableFuture<Iterable<Purchase>> getAllPurchases(){
        return CompletableFuture.supplyAsync(()->{
            CollectionDto<ClientDroidPrimaryKey, PurchaseDto> dto = template.getForObject("http://localhost:4000/purchases",CollectionDto.class);
            return dto.getElements().stream().map(d ->{
                var purchase = new Purchase(d.getId(),d.getClient(),d.getDroid(),d.getTotalPrice());
                purchase.setId(d.getId());
                return purchase;
            }).collect(Collectors.toList());},executorService);
    }
}
