package epam.com.JdbcAdvanced;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class JdbcAdvancedApplication implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAdvancedApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(JdbcAdvancedApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String users = """
                INSERT INTO users (id, name, surname, birthdate)\s
                SELECT generate_series,\s
                CONCAT('name ', generate_series) AS "name",\s
                CONCAT('surname ', generate_series) AS "surname",\s
                current_timestamp
                FROM generate_series(1, 1000);
                """;
//        jdbcTemplate.execute(users);

        String friendships = "INSERT INTO friendships (id, userid1, userid2, time) " +
                "SELECT generate_series, " +
                "floor(random() * 1000 + 1)::int, " +
                "floor(random() * 1000 + 1)::int, " +
                "current_timestamp " +
                "FROM generate_series (1, 70000)";
//        jdbcTemplate.execute(friendships);

        String posts = "INSERT INTO posts (id, userid, text, time) " +
                "SELECT generate_series, " +
                "floor(random() * 1000 + 1)::int, " +
                "CONCAT('text ', generate_series) AS \"text\", " +
                "current_timestamp " +
                "FROM generate_series (1, 5000)";
//        jdbcTemplate.execute(posts);

        String likes = "INSERT INTO likes(id, postid, userid, time) " +
                "SELECT generate_series, " +
                "floor(random() * 5000 + 1)::int, " +
                "floor(random() * 1000 + 1)::int, " +
                "current_timestamp " +
                "FROM generate_series (1, 300000)";
//        jdbcTemplate.execute(likes);


        String userList = """       
                SELECT u.name FROM users u
                      INNER JOIN
                      	(
                      		SELECT userid1
                      		FROM friendships
                      		GROUP BY userid1
                      		HAVING count(*) > 80
                      		ORDER BY userid1 asc
                      	) AS fsh
                      	ON u.id = fsh.userid1
                      INNER JOIN
                      	(
                      		SELECT userid, count(*) as "amount"
                      		FROM likes
                			WHERE likes.time < '2025-03-01'
                      		GROUP BY userid
                      		HAVING COUNT(*) > 100
                      		ORDER BY amount asc
                      	) AS lk
                     ON u.id = lk.userid;
                """;

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(userList);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }

        String fileStore = """
                CREATE TABLE file_storage 
                (id bigint PRIMARY KEY,
                filename VARCHAR(100),
                content VARCHAR,
                file_data bytea
                );
                """;
//        jdbcTemplate.execute(fileStore);
    }
}
