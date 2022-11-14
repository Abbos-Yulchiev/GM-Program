package com.study.springdataaccess.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InsufficientAmountException extends RuntimeException {

    long id;
    String message;

    public InsufficientAmountException(long id) {
        this.id = id;
    }

    public InsufficientAmountException(String s) {
    }
}
