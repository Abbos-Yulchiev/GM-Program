package epam.com.JdbcAdvanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Friendships {

    private long userId1;

    private long userId2;

    private LocalDateTime time;
}
