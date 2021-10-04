package repository.JPASpringRepo;

import domain.Keys.ClientDroidPrimaryKey;
import domain.baseEntities.JpaPurchase;
import domain.baseEntities.Purchase;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseRepo extends IRepository<JpaPurchase, ClientDroidPrimaryKey>{
}
