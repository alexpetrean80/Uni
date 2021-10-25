package core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan({"repository.JPASpringRepo", "service","domain.baseEntities"})
public class DealershipConfig {
}
