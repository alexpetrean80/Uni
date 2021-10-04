package service;

import domain.baseEntities.Receipt;
import exceptions.ExistingException;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptService {
    Repository<Long, Receipt> repository;

    /**
     *
     * @param repository
     */
    public ReceiptService(Repository<Long, Receipt> repository) {
        this.repository = repository;
    }

    /**
     *
     * @param newReceipt
     * @throws ExistingException
     */
    public void addReceipt(Receipt newReceipt) throws ExistingException, ClassNotFoundException {
        var result = repository.save(newReceipt);
        result.ifPresent(droid -> {

        });
    }

    /**
     *
     * @return
     */
    public List<Receipt> getReceipts() throws ClassNotFoundException {
        var receipts = new ArrayList<Receipt>();
        repository.findAll().forEach(receipts::add);
        return receipts;
    }

    /**
     *
     * @param price
     * @return
     */
    public List<Receipt> filterReceiptsByTotalPrice(double price) throws ClassNotFoundException {
        var receipts = new ArrayList<Receipt>();
        repository.findAll().forEach(receipts::add);
        return receipts.stream()
                .filter(s -> s.getTotalPrice() > price)
                .collect(Collectors.toList());
    }
}