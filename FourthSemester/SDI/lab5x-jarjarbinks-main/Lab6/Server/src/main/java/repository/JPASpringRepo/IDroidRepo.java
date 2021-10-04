package repository.JPASpringRepo;

import domain.baseEntities.Droid;
import org.springframework.stereotype.Repository;

@Repository
public interface IDroidRepo extends IRepository<Droid, Long>{
}
