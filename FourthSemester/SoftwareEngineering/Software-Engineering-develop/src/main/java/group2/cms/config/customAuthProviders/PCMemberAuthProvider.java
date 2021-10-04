package group2.cms.config.customAuthProviders;

import group2.cms.domain.PCMember;
import group2.cms.repository.GenericUserRepository;
import org.springframework.stereotype.Component;

@Component
public class PCMemberAuthProvider extends CustomAuthProvider<PCMember> {
    public PCMemberAuthProvider(GenericUserRepository<PCMember> repo) {
        super(repo);
    }
}
