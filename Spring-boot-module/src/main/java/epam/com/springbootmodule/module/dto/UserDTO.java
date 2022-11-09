package epam.com.springbootmodule.module.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private String role;

    private Long address;
}
