package server.rpc;

import domain.baseEntities.Purchase;
import exceptions.ExistingException;
import exceptions.NotFoundException;
import rpc.PurchaseRpc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class RpcPurchaseService implements PurchaseRpc {
    private final ExecutorService execSrv;
    private final service.PurchaseService srv;

    public RpcPurchaseService(ExecutorService execSrv, service.PurchaseService srv) {
        this.execSrv = execSrv;
        this.srv = srv;
    }

    public CompletableFuture<String> addPurchase(Long clientId, Long droidId, double totalPrice) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                srv.addPurchase(new Purchase(clientId, totalPrice, droidId));
                return "Purchase added successfully.";
            } catch (ExistingException | ClassNotFoundException e) {
                return e.getMessage();
            }
        }, execSrv);
    }

    public CompletableFuture<String> deletePurchase(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                srv.deletePurchase(id);
                return "Purchase deleted successfully.";
            } catch (NotFoundException | ClassNotFoundException e) {
                return e.getMessage();
            }
        }, execSrv);
    }

    public CompletableFuture<String> getAllPurchases() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return srv.getPurchases().toString();
            } catch (ClassNotFoundException e) {
                return e.getMessage();
            }
        }, execSrv);
    }
}
