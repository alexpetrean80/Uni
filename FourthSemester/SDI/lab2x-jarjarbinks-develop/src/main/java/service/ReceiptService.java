package service;

import domain.baseEntities.BaseEntity;
import domain.baseEntities.Receipt;
import repo.IRepository;
import domain.baseEntities.Droid;
import exceptions.ExistingDroidException;
import repo.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class ReceiptService {
    IRepository<Long, Receipt> repository;

    /**
     *
     * @param repository
     */
    public ReceiptService(IRepository<Long, Receipt> repository) {
        this.repository = repository;
    }

    /**
     *
     * @param newReceipt
     * @throws ExistingDroidException
     */
    public void addReceipt(Receipt newReceipt) throws ExistingDroidException {
        var result = repository.save(newReceipt);
        result.ifPresent(droid -> {

        });
    }

    /**
     *
     * @return
     */
    public List<Receipt> getReceipts() {
        var receipts = new ArrayList<Receipt>();
        repository.findAll().forEach(receipts::add);
        return receipts;
    }

    /**
     *
     * @param price
     * @return
     */
    public List<Receipt> filterReceiptsByTotalPrice(double price) {
        var receipts = new ArrayList<Receipt>();
        repository.findAll().forEach(receipts::add);
        return receipts.stream()
                .filter(s -> s.getTotalPrice() > price)
                .collect(Collectors.toList());
    }
}
