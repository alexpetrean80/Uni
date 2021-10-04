package service;

import domain.baseEntities.Droid;
import domain.baseEntities.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.JPASpringRepo.IReceiptRepo;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Math.max;


@Service
public class ReceiptServiceSrv implements ReceiptService {

    @Autowired
    private IReceiptRepo repository;

    @Override
    public String addReceipt(Long purchaseId, double totalPrice) {
      var newReceipt = new Receipt(purchaseId, totalPrice);
      try{
          long id = 0;
          for(Receipt d : this.repository.findAll())
              id = max(id, d.getId() + 1);
          newReceipt.setId(id);
          var result = repository.save(newReceipt);
          AtomicReference<String> resMsg = new AtomicReference<>("Receipt saved successfully.");
          return resMsg.get();
      }catch (Exception e){
          return e.getMessage();
      }
    }

    @Override
    public List<Receipt> getAllReceipts() {
        var receipts = new ArrayList<Receipt>();
        try {
            repository.findAll().forEach(receipts::add);
            return receipts;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}