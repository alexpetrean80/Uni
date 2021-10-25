package repository;

import domain.Keys.ClientDroidPrimaryKey;
import domain.baseEntities.Purchase;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseRepo extends IRepository<Purchase, ClientDroidPrimaryKey>{
}
