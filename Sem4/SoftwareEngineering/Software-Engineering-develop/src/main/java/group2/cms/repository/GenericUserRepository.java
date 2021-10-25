package group2.cms.repository;

import group2.cms.domain.BaseEntity;
import group2.cms.domain.CMSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface GenericUserRepository<T extends CMSUser> extends JpaRepository<T, Long> {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<T> findByUsername(String username);

    Optional<T> findByEmail(String email);
}
