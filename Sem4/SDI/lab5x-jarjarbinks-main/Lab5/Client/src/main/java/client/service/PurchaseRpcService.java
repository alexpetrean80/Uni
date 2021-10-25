package client.service;

import client.TcpClient;
import rpc.Message;
import rpc.PurchaseRpc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class PurchaseRpcService implements PurchaseRpc {
    private final ExecutorService execserv;
    private final TcpClient tcli;

    public PurchaseRpcService(ExecutorService execserv, TcpClient tcli) {
        this.execserv = execserv;
        this.tcli = tcli;
    }

    @Override
    public CompletableFuture<String> addPurchase(Long clientId, Long droidId, double totalPrice) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(PurchaseRpc.ADD_PURCHASE,clientId.toString()+","+droidId.toString()+","+Double.toString(totalPrice));
            return tcli.sendAndReceive(req).getBody();
        },execserv);
    }

    @Override
    public CompletableFuture<String> deletePurchase(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(PurchaseRpc.DELETE_PURCHASE,id.toString());
            return tcli.sendAndReceive(req).getBody();
        },execserv);
    }

    @Override
    public CompletableFuture<String> getAllPurchases() {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(PurchaseRpc.GET_ALL_PURCHASES,"");
            return tcli.sendAndReceive(req).getBody();
        },execserv);
    }
}
