package group2.cms.config.customAuthProviders;

import group2.cms.domain.CoChair;
import group2.cms.repository.GenericUserRepository;
import org.springframework.stereotype.Component;

@Component
public class CoChairAuthProvider extends CustomAuthProvider<CoChair> {
    public CoChairAuthProvider(GenericUserRepository<CoChair> repo) {
        super(repo);
    }
}
