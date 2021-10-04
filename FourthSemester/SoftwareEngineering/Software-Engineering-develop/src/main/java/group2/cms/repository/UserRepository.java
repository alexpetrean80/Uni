package group2.cms.repository;

import group2.cms.domain.CMSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericUserRepository<CMSUser> {
}
