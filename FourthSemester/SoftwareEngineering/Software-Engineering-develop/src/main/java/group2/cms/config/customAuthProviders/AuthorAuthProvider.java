package group2.cms.config.customAuthProviders;

import group2.cms.domain.Author;
import group2.cms.repository.GenericUserRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorAuthProvider extends CustomAuthProvider<Author> {
    public AuthorAuthProvider(GenericUserRepository<Author> repo) {
        super(repo);
    }
}
