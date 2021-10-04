package group2.cms.config.customAuthProviders;

import group2.cms.domain.CMSUser;
import group2.cms.repository.GenericUserRepository;
import org.springframework.stereotype.Component;

@Component
public class CMSUserAuthProvider extends CustomAuthProvider<CMSUser> {
    public CMSUserAuthProvider(GenericUserRepository<CMSUser> repo) {
        super(repo);
    }
}
