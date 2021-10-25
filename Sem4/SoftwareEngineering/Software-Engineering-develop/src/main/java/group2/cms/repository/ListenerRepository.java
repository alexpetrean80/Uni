package group2.cms.repository;

import group2.cms.domain.Listener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListenerRepository extends GenericUserRepository<Listener> {
}
