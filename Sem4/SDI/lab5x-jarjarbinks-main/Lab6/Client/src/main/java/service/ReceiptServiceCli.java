package service;

import domain.baseEntities.Receipt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReceiptServiceCli implements ReceiptService {
    @Autowired
    ReceiptService receiptService;


    @Override
    public String addReceipt(Long purchaseId, double totalPrice) {
        return receiptService.addReceipt(purchaseId,totalPrice);
    }

    @Override
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }
}
