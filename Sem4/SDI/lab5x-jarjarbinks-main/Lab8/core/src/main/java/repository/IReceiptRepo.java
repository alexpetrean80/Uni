package repository;

import domain.baseEntities.Receipt;
import org.springframework.stereotype.Repository;

@Repository
public interface IReceiptRepo extends IRepository<Receipt, Long>{
}
