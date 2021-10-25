package rpc;

import domain.baseEntities.Receipt;

import java.util.concurrent.CompletableFuture;

public interface ReceiptRpc {
    int PORT = 3000;
    String HOST = "localhost";

    String ADD_RECEIPT = "addReceipt";
    String GET_ALL_RECEIPTS = "getAllReceipts";

    CompletableFuture<String> addReceipt(Long purchaseId, double totalPrice);

    CompletableFuture<String> getAllReceipts();
}
