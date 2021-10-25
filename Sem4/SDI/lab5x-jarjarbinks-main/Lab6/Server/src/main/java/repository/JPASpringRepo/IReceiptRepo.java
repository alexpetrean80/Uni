package repository.JPASpringRepo;

import domain.Keys.ClientDroidPrimaryKey;
import domain.baseEntities.Purchase;
import domain.baseEntities.Receipt;
import org.springframework.stereotype.Repository;

@Repository
public interface IReceiptRepo extends IRepository<Receipt, Long>{
}
