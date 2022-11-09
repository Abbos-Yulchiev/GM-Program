package epam.com.JdbcAdvanced.repository;

import epam.com.JdbcAdvanced.model.dto.Carrier;
import org.springframework.stereotype.Repository;

public interface DBConnectionRepository {

    String addTable(Carrier carrier);
}
