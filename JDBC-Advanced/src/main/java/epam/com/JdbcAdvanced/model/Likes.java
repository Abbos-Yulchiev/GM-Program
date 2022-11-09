package epam.com.JdbcAdvanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Likes {

    private long postId;

    private long userId;

    private LocalDateTime time;

}
