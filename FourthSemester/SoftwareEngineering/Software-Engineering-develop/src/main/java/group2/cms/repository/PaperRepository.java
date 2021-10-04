package group2.cms.repository;

import group2.cms.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaperRepository extends JpaRepository<Paper,Long> {

    @Query(value="select * from paper p where p.title = ?1 limit 1", nativeQuery = true)
    Optional<Paper> findPaperByTitle(String title);
}
