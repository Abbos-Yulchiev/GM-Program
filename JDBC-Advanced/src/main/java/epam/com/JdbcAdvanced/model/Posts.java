package epam.com.JdbcAdvanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Posts {

    private long id;

    private long user;

    private String text;

    private LocalDateTime time;

}
