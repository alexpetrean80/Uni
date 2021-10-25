package server.rpc;

import domain.baseEntities.Receipt;
import exceptions.ExistingException;
import rpc.ReceiptRpc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class RpcReceiptService implements ReceiptRpc {
    private final ExecutorService execSrv;
    private service.ReceiptService service;

    public RpcReceiptService(ExecutorService execSrv, service.ReceiptService service) {
        this.execSrv = execSrv;
        this.service = service;
    }

    public CompletableFuture<String> addReceipt(Long purchaseId, double totalPrice) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                service.addReceipt(new Receipt(purchaseId, totalPrice));
                return "Receipt added successfully.";
            } catch (ExistingException | ClassNotFoundException e) {
                return e.getMessage();
            }
        }, execSrv);
    }

    public CompletableFuture<String> getAllReceipts() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return service.getReceipts().toString();
            } catch (ClassNotFoundException e) {
                return e.getMessage();
            }
        }, execSrv);
    }

}
