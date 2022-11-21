package epam.com.JdbcAdvanced.repository.impl;

import epam.com.JdbcAdvanced.repository.DBConnectionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class DBConnectionRepositoryImpl implements DBConnectionRepository {

    private final JdbcTemplate jdbcTemplate;
    Random random = new Random();


    public DBConnectionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String addTable() {

        int randomTableCount = random.nextInt(1, 5);
        for (int i = 0; i < randomTableCount; i++) {
            jdbcTemplate.execute(queryBuilder());
        }
        return "Table created and row added with data";
    }

    private String generateRandomWord() {
        StringBuilder word = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            char tmp = (char) ('a' + random.nextInt('z' - 'a'));
            word.append(tmp);
        }
        return word.toString();
    }

    private String queryBuilder() {

        String[] types = {"bigint", "serial", "boolean", "integer", "character varying", "timestamp without time zone", "bytea"};
        StringBuilder query = new StringBuilder(
                "CREATE TABLE IF NOT EXISTS " + generateRandomWord() +
                        " (id bigint Primary key"
        );

        int randomColumnCount = random.nextInt(1, 20);
        for (int j = 0; j < randomColumnCount; j++) {
            query
                    .append(", ")
                    .append(generateRandomWord())
                    .append(" ")
                    .append(types[random.nextInt(1, 7)]);
        }
        query.append(")");
        return query.toString();
    }
}
