package service.implementedServices;

import domain.Keys.ClientDroidPrimaryKey;
import domain.baseEntities.Client;
import domain.baseEntities.Droid;
import domain.baseEntities.Purchase;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import repository.*;
import service.interfaces.PurchaseService;


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
                Purchase purchase = new Purchase(
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
    public List<Purchase> getAllPurchases() {
        var purchases = new ArrayList<Purchase>();
        try {
            purchases.addAll(purchaseRepository.findAll());
            return purchases;
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
