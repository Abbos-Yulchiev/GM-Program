package com.epam.cicddemo.models;


import com.epam.cicddemo.models.enums.JobTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee extends User {

    private Department department;
    private JobTitle jobTitle;

}
