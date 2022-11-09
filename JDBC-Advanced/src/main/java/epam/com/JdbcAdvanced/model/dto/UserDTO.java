package epam.com.JdbcAdvanced.model.dto;

import lombok.Data;

@Data
public class UserDTO {

    private long id;
    private String name;
    private String surname;
    private String birthday;
}
