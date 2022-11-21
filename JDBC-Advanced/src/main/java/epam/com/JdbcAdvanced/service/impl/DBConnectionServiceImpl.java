package epam.com.JdbcAdvanced.service.impl;


import epam.com.JdbcAdvanced.repository.DBConnectionRepository;
import epam.com.JdbcAdvanced.service.DBConnectionService;
import org.springframework.stereotype.Service;

@Service
public class DBConnectionServiceImpl implements DBConnectionService {


    private final DBConnectionRepository dbConnectionRepository;

    public DBConnectionServiceImpl(DBConnectionRepository dbConnectionRepository) {
        this.dbConnectionRepository = dbConnectionRepository;
    }

    @Override
    public String addTable() {
        return dbConnectionRepository.addTable();
    }
}
