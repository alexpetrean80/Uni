package service.interfaces;

import domain.baseEntities.Purchase;

import java.util.List;

public interface PurchaseService {

    String addPurchase(Long clientId, Long droidId, double totalPrice);

    String deletePurchase(Long id);

    List<Purchase> getAllPurchases();
}
