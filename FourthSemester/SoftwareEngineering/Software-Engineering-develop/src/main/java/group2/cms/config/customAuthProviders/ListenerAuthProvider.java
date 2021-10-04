package group2.cms.config.customAuthProviders;

import group2.cms.domain.Listener;
import group2.cms.repository.GenericUserRepository;
import org.springframework.stereotype.Component;

@Component
public class ListenerAuthProvider extends CustomAuthProvider<Listener> {
    public ListenerAuthProvider(GenericUserRepository<Listener> repo) {
        super(repo);
    }
}
