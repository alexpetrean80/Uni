package rpc;

import domain.baseEntities.Droid;

import java.util.concurrent.CompletableFuture;

public interface DroidRpc {
    String ADD_DROID = "addDroid";
    String DELETE_DROID = "deleteDroid";
    String GET_ALL_DROIDS = "getAllDroids";
    String GET_DROIDS_BY_FILTER = "getDroidsByFilter";
    String GET_DROIDS_BY_ID = "getDroidsById";
    String GET_DROIDS_BY_MODEL = "getDroidByModel";
    String UPDATE_DROID = "updateDroid";

    CompletableFuture<String> addDroid(double powerUsage, double price, int batteryTime, String model, boolean driver);

    CompletableFuture<String> deleteDroid(Long id);

    CompletableFuture<String> getAllDroids();

    CompletableFuture<String> getDroidsByFilter(int filter);

    CompletableFuture<String> getDroidsById(Long id);

    CompletableFuture<String> getDroidsByModel(String model);

    CompletableFuture<String> updateDroid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver);

}
