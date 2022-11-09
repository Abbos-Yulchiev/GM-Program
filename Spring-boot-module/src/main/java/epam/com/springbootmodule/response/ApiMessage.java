package epam.com.springbootmodule.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiMessage {

    String message;

    HttpStatus status;
}
