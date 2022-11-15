package epam.com.JdbcAdvanced.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
//@ComponentScan("orm.jdbc")
public class SpringJdbcConfig {

    @Bean
    public DataSource postgresqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/jdbc-advanced");
        dataSource.setUsername("postgres");
        dataSource.setPassword("mirzaolim");
        return dataSource;
    }
}
