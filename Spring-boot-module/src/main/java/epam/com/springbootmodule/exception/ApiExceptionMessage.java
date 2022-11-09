package epam.com.springbootmodule.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiExceptionMessage {

    public ApiExceptionMessage(String message, HttpStatus statusCode, LocalDateTime time) {
    }
}
