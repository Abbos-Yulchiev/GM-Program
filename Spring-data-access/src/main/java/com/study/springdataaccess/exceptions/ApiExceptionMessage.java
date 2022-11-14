package com.study.springdataaccess.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiExceptionMessage {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime time;
}
