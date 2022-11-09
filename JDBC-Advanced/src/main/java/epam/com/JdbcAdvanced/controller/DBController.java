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

    @PostMapping("/addTable")
    public String addTable(@RequestBody Carrier carrier) {
        return dbConnectionService.addTable(carrier);
    }

}
