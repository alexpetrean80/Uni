package repository.JPASpringRepo;

import domain.baseEntities.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepo extends IRepository<Client, Long>{
}
