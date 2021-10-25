package client.service;

import client.TcpClient;
import rpc.Message;
import rpc.ReceiptRpc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ReceiptRpcService implements ReceiptRpc {
    private final ExecutorService execsrv;
    private final TcpClient tcli;

    public ReceiptRpcService(ExecutorService execsrv, TcpClient tcli) {
        this.execsrv = execsrv;
        this.tcli = tcli;
    }

    @Override
    public CompletableFuture<String> addReceipt(Long purchaseId, double totalPrice) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(ReceiptRpc.ADD_RECEIPT,purchaseId.toString()+","+Double.toString(totalPrice));
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> getAllReceipts() {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(ReceiptRpc.GET_ALL_RECEIPTS,"");
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }
}
