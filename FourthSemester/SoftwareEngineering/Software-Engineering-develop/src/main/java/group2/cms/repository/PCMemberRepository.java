package group2.cms.repository;

import group2.cms.domain.PCMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PCMemberRepository extends GenericUserRepository<PCMember> {
    List<PCMember> findAllByAffiliation(String affiliation);
}
