package service;

import domain.baseEntities.Purchase;
import exceptions.ExistingException;
import exceptions.NotFoundException;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PurchaseService {
    Repository<Long, Purchase> repository;

    /**
     *
     * @param repository
     */
    public PurchaseService(Repository<Long, Purchase> repository){
        this.repository = repository;
    }

    /**
     *
     * @param newPurchase
     * @throws ExistingException
     */
    public void addPurchase(Purchase newPurchase) throws ExistingException, ClassNotFoundException {

        var result = this.repository.save(newPurchase);
        result.ifPresent(p -> {
            throw new ExistingException("Purchase already exists");
        });

    }

    /**
     *
     * @return
     */
    public List<Purchase> getPurchases() throws ClassNotFoundException {
        var purchases = new ArrayList<Purchase>();
        this.repository.findAll().forEach(purchases::add);
        return purchases;
    }

    /**
     *
     * @param id
     * @throws NotFoundException
     */
    public void deletePurchase(Long id) throws NotFoundException, ClassNotFoundException {
        var result = this.repository.delete(id);
        result.ifPresentOrElse(p -> {},
                () -> {
                    throw new NotFoundException("Purchase does not exist");
                });
    }
}
