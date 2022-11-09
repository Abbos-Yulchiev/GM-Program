package com.epam.cicddemo.models.DTO;

import com.epam.cicddemo.models.enums.Gender;
import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private Integer age;
}
