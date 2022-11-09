package epam.com.JdbcAdvanced.orm.jdbc;

import epam.com.JdbcAdvanced.config.SpringJdbcConfig;
import org.springframework.boot.CommandLineRunner;

import java.sql.Connection;

public class DemoData implements CommandLineRunner {

    private final SpringJdbcConfig config;

    public DemoData(SpringJdbcConfig config) {
        this.config = config;
    }

    @Override
    public void run(String... args) throws Exception {
        Connection connection = config.postgresqlDataSource().getConnection();
//        connection.
    }
}
