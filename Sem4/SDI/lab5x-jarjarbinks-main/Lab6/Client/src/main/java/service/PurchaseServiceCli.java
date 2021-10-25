package service;

import domain.baseEntities.JpaPurchase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PurchaseServiceCli implements PurchaseService {
   @Autowired
   PurchaseService purchaseService;

    @Override
    public String addPurchase(Long clientId, Long droidId, double totalPrice) {
        return purchaseService.addPurchase(clientId,droidId,totalPrice);
    }

    @Override
    public String deletePurchase(Long id) {
        return purchaseService.deletePurchase(id);
    }

    @Override
    public List<JpaPurchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }
}
