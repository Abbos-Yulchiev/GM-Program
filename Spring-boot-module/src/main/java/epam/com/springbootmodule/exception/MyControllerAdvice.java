package epam.com.springbootmodule.exception;

import epam.com.springbootmodule.exception.ApiExceptionMessage;
import epam.com.springbootmodule.exception.NotFoundException;
import epam.com.springbootmodule.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<ApiExceptionMessage> handlerNullPointerException(NullPointerException e) {
        return new ResponseEntity<>(new ApiExceptionMessage(e.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public Response<Object> handlerNotFoundException(NotFoundException e) {
        return new Response<>(new ApiExceptionMessage(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()));
    }
}
