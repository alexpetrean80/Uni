package service;

import domain.Keys.ClientDroidPrimaryKey;
import domain.baseEntities.Client;
import domain.baseEntities.Droid;
import domain.baseEntities.JpaPurchase;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.JPASpringRepo.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


public class PurchaseServiceSrv implements PurchaseService {

    @Autowired
    private IPurchaseRepo purchaseRepository;

    @Autowired
    private IDroidRepo droidRepo;

    @Autowired
    private IClientRepo clientRepository;





    @Override
    public String addPurchase(Long clientId, Long droidId, double totalPrice) {
        AtomicReference<String> resMsg = new AtomicReference<>("Purchase saved successfully.");
        Optional<Droid> droid = droidRepo.findById(droidId);
        droid.ifPresentOrElse((Droid d) ->{
            Optional<Client> client = clientRepository.findById(clientId);
            client.ifPresentOrElse((Client c) -> {
                JpaPurchase purchase = new JpaPurchase(
                        new ClientDroidPrimaryKey(c.getId(),d.getId()),c,d,totalPrice);
                purchaseRepository.save(purchase);
            }, () -> {
                throw new NotFoundException("Client does not exist!");
            });
        }, () -> {
            throw new NotFoundException("Droid does not exist!");
        });
        return resMsg.get();
    }

    @Override
    public String deletePurchase(Long id) {
        AtomicReference<String> resMsg = new AtomicReference<>("Purchase deleted successfully.");
        var found = purchaseRepository.findAll();
        var p = found.stream().filter(pr -> pr.getId().equals(id)).findAny();
        purchaseRepository.findById(p.get().getId())
                .ifPresentOrElse(
                        purchase -> purchaseRepository.deleteById(purchase.getId()),
                        () -> {throw new NotFoundException("Purchase does not exist");}
                );
        return resMsg.get();
    }


    @Override
    public List<JpaPurchase> getAllPurchases() {
        var purchases = new ArrayList<JpaPurchase>();
        try {
            purchases.addAll(purchaseRepository.findAll());
            return purchases;
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
