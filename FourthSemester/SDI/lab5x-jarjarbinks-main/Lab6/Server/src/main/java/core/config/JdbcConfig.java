package core.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    @Bean
    JdbcOperations jdbcOperations() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUsername("nngzqljx");
        dataSource.setPassword("xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        dataSource.setUrl("jdbc:postgresql://tai.config.resources.core.config.local.db.elephantsql.com:5432/nngzqljx");
        dataSource.setInitialSize(2);
        return dataSource;
    }
}
