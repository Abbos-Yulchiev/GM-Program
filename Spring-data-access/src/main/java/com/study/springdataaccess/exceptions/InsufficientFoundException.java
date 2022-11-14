package com.study.springdataaccess.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InsufficientFoundException extends RuntimeException {

    private long id;
    private String message;

    public InsufficientFoundException(long id) {
        this.id = id;
    }

    public InsufficientFoundException(String message) {
        this.message = message;
    }
}
