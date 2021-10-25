package ui.controllers;


import domain.baseEntities.Client;
import domain.baseEntities.Droid;
import dto.ClientDto;
import dto.CollectionDto;
import dto.DroidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class DroidController {

    @Autowired
    ExecutorService executorService;

    @Autowired
    RestTemplate template;

    public CompletableFuture<String> addDroid(double powerUsage, double price, int batteryTime, String model, boolean driver){
        return CompletableFuture.supplyAsync(()->{
            var dto = new DroidDto(powerUsage,price,batteryTime,model,driver);
            template.postForObject("http://localhost:4000/droid",dto,DroidDto.class);
            return "Droid added. ";},executorService);
    }

    public CompletableFuture<String> deleteDroid(Long id){
        return CompletableFuture.supplyAsync(()->{
            template.delete("http://localhost:4000/droid/{id}",id);
            return "Droid deleted.";},executorService);
    }

    public CompletableFuture<String> updateDroid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver){
        return CompletableFuture.supplyAsync(()->{
            var dto = new DroidDto(powerUsage,price,batteryTime,model,driver);
            template.put("http://localhost:4000/droid/{id}",dto,id);
            return "Droid updated.";},executorService);
    }

    public CompletableFuture<Iterable<Droid>> getDroidsById(Long id){
        return CompletableFuture.supplyAsync(() -> {
            CollectionDto<Long, DroidDto> dto = template.getForObject("http://localhost:4000/droids/{id}", CollectionDto.class,id);
            return dto.getElements().stream().map(d -> {
                var droid = new Droid(d.getPowerUsage(), d.getPrice(), d.getBatteryTime(),d.getModel(),d.isDriver());
                droid.setId(d.getId());
                return droid;
            }).collect(Collectors.toList());
        }, executorService);
    }

    public CompletableFuture<Iterable<Droid>> getDroidsByModel(String model){
        return CompletableFuture.supplyAsync(()->{
            CollectionDto<Long,DroidDto> dto = template.getForObject("http://localhost:4000/droids/{model}",CollectionDto.class,model);
            return dto.getElements().stream().map(d ->{
                var droid = new Droid(d.getPowerUsage(),d.getPrice(),d.getBatteryTime(),d.getModel(),d.isDriver());
                droid.setId(d.getId());
                return droid;
            }).collect(Collectors.toList());},executorService);
    }

    public CompletableFuture<Iterable<Droid>> getDroidsByMinimumPowerUsage(int min){
        return CompletableFuture.supplyAsync(()->{
            CollectionDto<Long,DroidDto> dto = template.getForObject("http://localhost:4000/droids/{power}",CollectionDto.class,min);
            return dto.getElements().stream().map(d ->{
                var droid = new Droid(d.getPowerUsage(),d.getPrice(),d.getBatteryTime(),d.getModel(),d.isDriver());
                droid.setId(d.getId());
                return droid;
            }).collect(Collectors.toList());},executorService);
    }

    public CompletableFuture<Iterable<Droid>> getAllDroids(){
        return CompletableFuture.supplyAsync(()->{
            CollectionDto<Long,DroidDto> dto = template.getForObject("http://localhost:4000/droids",CollectionDto.class);
            return dto.getElements().stream().map(d ->{
                var droid = new Droid(d.getPowerUsage(),d.getPrice(),d.getBatteryTime(),d.getModel(),d.isDriver());
                droid.setId(d.getId());
                return droid;
            }).collect(Collectors.toList());},executorService);
    }
}
