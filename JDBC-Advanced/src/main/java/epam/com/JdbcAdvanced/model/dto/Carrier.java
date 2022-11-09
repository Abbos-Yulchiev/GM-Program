package epam.com.JdbcAdvanced.model.dto;

import lombok.Data;

@Data
public class Carrier {

    private String name;
    private long startingPoint;
    private long endPoint;

}
