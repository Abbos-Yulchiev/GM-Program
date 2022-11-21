package epam.com.JdbcAdvanced.controller;

import epam.com.JdbcAdvanced.model.dto.Carrier;
import epam.com.JdbcAdvanced.service.DBConnectionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dbConnection")
public class DBController {


    private DBConnectionService dbConnectionService;

    public DBController(DBConnectionService dbConnectionService) {
        this.dbConnectionService = dbConnectionService;
    }

    @PostMapping
    public String addTable() {
        return dbConnectionService.addTable();
    }
}
