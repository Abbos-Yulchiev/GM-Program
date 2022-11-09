package epam.com.JdbcAdvanced.repository.impl;

import epam.com.JdbcAdvanced.model.dto.Carrier;
import epam.com.JdbcAdvanced.repository.DBConnectionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBConnectionRepositoryImpl implements DBConnectionRepository {

    private final JdbcTemplate jdbcTemplate;

    public DBConnectionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String addTable(Carrier carrier) {

        int upperBound = (carrier.getEndPoint() > 10L) ? 5 : (int) carrier.getEndPoint();
        for (int i = 1; i <= upperBound; i++) {
            String sql = "CREATE TABLE " + carrier.getName() + "_" + i + " " +
                    "(id bigint Primary key, " +
                    "row_" + i + " VARCHAR (50) NOT NULL, " +
                    "row_" + (i + 1) + " VARCHAR (50) NOT NULL);";
            jdbcTemplate.execute(sql);

            String rows = "INSERT INTO " + carrier.getName() + "_" + i + " " +
                    "(id, row_" + i + ", row_" + (i + 1) + ") " +
                    "SELECT generate_series, " +
                    "'info-" + i + "', " +
                    "'additional info-" + i + "' " +
                    "FROM generate_series (1, " + carrier.getEndPoint() + ")";
            jdbcTemplate.execute(rows);
        }
        return "Table created and row added with data";
    }
}
