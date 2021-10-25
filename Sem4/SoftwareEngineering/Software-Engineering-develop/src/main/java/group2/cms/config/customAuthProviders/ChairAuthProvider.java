package group2.cms.config.customAuthProviders;

import group2.cms.domain.Chair;
import group2.cms.repository.GenericUserRepository;
import org.springframework.stereotype.Component;

@Component
public class ChairAuthProvider extends CustomAuthProvider<Chair> {
    public ChairAuthProvider(GenericUserRepository<Chair> repo) {
        super(repo);
    }
}
