package service;

import domain.baseEntities.JpaPurchase;

import java.util.List;

public interface PurchaseService {

    String addPurchase(Long clientId, Long droidId, double totalPrice);

    String deletePurchase(Long id);

    List<JpaPurchase> getAllPurchases();
}
