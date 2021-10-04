package service;

import domain.baseEntities.Purchase;
import exceptions.ExistingPurchaseException;
import exceptions.InexistentPurchaseException;
import repo.IRepository;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PurchaseService {
    IRepository<Long, Purchase> repository;

    /**
     *
     * @param repository
     */
    public PurchaseService(IRepository<Long, Purchase> repository){
        this.repository = repository;
    }

    /**
     *
     * @param newPurchase
     * @throws ExistingPurchaseException
     */
    public void addPurchase(Purchase newPurchase) throws ExistingPurchaseException {

        var result = this.repository.save(newPurchase);
        result.ifPresent(p -> {
            throw new ExistingPurchaseException("Purchase already exists");
        });

    }

    /**
     *
     * @return
     */
    public List<Purchase> getPurchases(){
        var purchases = new ArrayList<Purchase>();
        this.repository.findAll().forEach(purchases::add);
        return purchases;
    }

    /**
     *
     * @param id
     * @throws InexistentPurchaseException
     */
    public void deletePurchase(Long id) throws InexistentPurchaseException {
        var result = this.repository.delete(id);
        result.ifPresentOrElse(p -> {},
                () -> {
            throw new InexistentPurchaseException("Purchase does not exist");
                });
    }
}
