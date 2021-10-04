package group2.cms.repository;

import group2.cms.domain.CoChair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoChairRepository extends GenericUserRepository<CoChair> {
}
