package group2.cms.repository;

import group2.cms.domain.Author;
import group2.cms.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends GenericUserRepository<Author> {
    List<Author> findAuthorsByPaper(Paper paper);

    Optional<Author> findAuthorByUsername(String username);

    Optional<Author> findAuthorByEmail(String email);
}
