package rpc;

import domain.baseEntities.Purchase;

import java.util.concurrent.CompletableFuture;

public interface PurchaseRpc {
    int PORT = 3000;
    String HOST = "localhost";

    String ADD_PURCHASE = "addPurchase";
    String DELETE_PURCHASE = "deletePurchase";
    String GET_ALL_PURCHASES = "getAllPurchases";

    CompletableFuture<String> addPurchase(Long clientId, Long droidId, double totalPrice);

    CompletableFuture<String> deletePurchase(Long id);

    CompletableFuture<String> getAllPurchases();
}
