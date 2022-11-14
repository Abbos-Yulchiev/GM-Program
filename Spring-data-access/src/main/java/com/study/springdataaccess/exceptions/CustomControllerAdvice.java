package com.study.springdataaccess.exceptions;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler implements ResponseErrorHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<ApiExceptionMessage> handlerNullPointerException(NullPointerException e) {
        return new ResponseEntity<>(new ApiExceptionMessage(e.getMessage(),
                HttpStatus.CONFLICT, LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiExceptionMessage> handlerNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new ApiExceptionMessage(e.getMessage(),
                HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientFoundException.class)
    public ResponseEntity<ApiExceptionMessage> insufficientFoundException(InsufficientFoundException e) {
        return new ResponseEntity<>(new ApiExceptionMessage(e.getMessage(),
                HttpStatus.CONFLICT, LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InsufficientAmountException.class)
    public ResponseEntity<ApiExceptionMessage> insufficientAmountException(InsufficientAmountException e) {
        return new ResponseEntity<>(new ApiExceptionMessage(e.getMessage(),
                HttpStatus.CONFLICT, LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ApiExceptionMessage> handlerEmptyResultDataAccessException(NotFoundException e) {
        return new ResponseEntity<>(new ApiExceptionMessage(e.getMessage(),
                HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NotFoundException("Citizen Id found!", 10140L);
        }
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }
}
