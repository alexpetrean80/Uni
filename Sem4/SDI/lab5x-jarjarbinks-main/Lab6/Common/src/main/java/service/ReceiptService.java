package service;

import domain.baseEntities.Receipt;

import java.util.List;

public interface ReceiptService {

    String addReceipt(Long purchaseId, double totalPrice);

    List<Receipt> getAllReceipts();
}
