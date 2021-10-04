package group2.cms.repository;

import group2.cms.domain.PaperFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperFileRepository extends JpaRepository<PaperFile, Long> {
}
