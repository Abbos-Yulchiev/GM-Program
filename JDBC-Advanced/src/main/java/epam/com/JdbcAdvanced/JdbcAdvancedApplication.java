package epam.com.JdbcAdvanced;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {

        String userTable = """
                CREATE TABLE IF NOT EXISTS public.users
                (
                    id bigint NOT NULL,
                    name character varying COLLATE pg_catalog."default",
                    surname character varying COLLATE pg_catalog."default",
                    birthdate timestamp without time zone,
                    CONSTRAINT users_pkey PRIMARY KEY (id)
                );
                """;

        String postsTable = """
                CREATE TABLE IF NOT EXISTS public.posts
                (
                    id bigint NOT NULL,
                    userid bigint,
                    text character varying(250) COLLATE pg_catalog."default",
                    "time" timestamp without time zone,
                    CONSTRAINT posts_pkey PRIMARY KEY (id),
                    CONSTRAINT fk_post_user FOREIGN KEY (userid)
                        REFERENCES public.users (id) MATCH SIMPLE
                        ON UPDATE NO ACTION
                        ON DELETE NO ACTION
                )
                """;

        String likesTable = """
                CREATE TABLE IF NOT EXISTS public.likes
                (
                    id bigint NOT NULL,
                    userid bigint,
                    postid bigint,
                    "time" timestamp without time zone,
                    CONSTRAINT likes_pkey PRIMARY KEY (id),
                    CONSTRAINT fk_likes_post FOREIGN KEY (postid)
                        REFERENCES public.posts (id) MATCH SIMPLE
                        ON UPDATE NO ACTION
                        ON DELETE NO ACTION
                        NOT VALID,
                    CONSTRAINT fk_likes_user FOREIGN KEY (userid)
                        REFERENCES public.users (id) MATCH SIMPLE
                        ON UPDATE NO ACTION
                        ON DELETE NO ACTION
                )
                """;

        String friendshipsTable = """
                CREATE TABLE IF NOT EXISTS public.friendships
                (
                    userid1 bigint,
                    userid2 bigint,
                    id bigint,
                    "time" timestamp without time zone,
                    CONSTRAINT "fk_likes_userId1" FOREIGN KEY (userid1)
                        REFERENCES public.users (id) MATCH SIMPLE
                        ON UPDATE NO ACTION
                        ON DELETE NO ACTION,
                    CONSTRAINT "fk_likes_userId2" FOREIGN KEY (userid2)
                        REFERENCES public.users (id) MATCH SIMPLE
                        ON UPDATE NO ACTION
                        ON DELETE NO ACTION
                )
                """;

        String fileStorageTable = """
                CREATE TABLE IF NOT EXISTS public.file_storage
                (
                    id bigint NOT NULL,
                    filename character varying COLLATE pg_catalog."default",
                    content character varying COLLATE pg_catalog."default",
                    bytes bytea,
                    CONSTRAINT file_storage_pkey PRIMARY KEY (id)
                )
                """;

        String users = """
                INSERT INTO users (id, name, surname, birthdate)\s
                SELECT generate_series,\s
                CONCAT('name ', generate_series) AS "name",\s
                CONCAT('surname ', generate_series) AS "surname",\s
                current_timestamp
                FROM generate_series(1, 1000);
                """;

        String friendships = "INSERT INTO friendships (id, userid1, userid2, time) " +
                "SELECT generate_series, " +
                "floor(random() * 1000 + 1)::int, " +
                "floor(random() * 1000 + 1)::int, " +
                "current_timestamp " +
                "FROM generate_series (1, 70000)";

        String posts = "INSERT INTO posts (id, userid, text, time) " +
                "SELECT generate_series, " +
                "floor(random() * 1000 + 1)::int, " +
                "CONCAT('text ', generate_series) AS \"text\", " +
                "current_timestamp " +
                "FROM generate_series (1, 5000)";

        String likes = "INSERT INTO likes(id, postid, userid, time) " +
                "SELECT generate_series, " +
                "floor(random() * 5000 + 1)::int, " +
                "floor(random() * 1000 + 1)::int, " +
                "current_timestamp " +
                "FROM generate_series (1, 300000)";

        String filesStorage = """
                insert into file_storage (id, filename, content)
                values (1, 'image', 'img/jpg');
                """;

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

        /**
         * Tables remover
         */
        remover();

        jdbcTemplate.execute(userTable);
        jdbcTemplate.execute(postsTable);
        jdbcTemplate.execute(likesTable);
        jdbcTemplate.execute(friendshipsTable);
        jdbcTemplate.execute(fileStorageTable);
        jdbcTemplate.execute(users);
        jdbcTemplate.execute(friendships);
        jdbcTemplate.execute(posts);
        jdbcTemplate.execute(likes);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(userList);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    void remover() {
        String removeFriendships = "DROP TABLE friendships;";
        String removeLikes = "DROP TABLE likes;";
        String removePosts = "DROP TABLE posts;";
        String removeUsers = "DROP TABLE users;";

        jdbcTemplate.execute(removeFriendships);
        jdbcTemplate.execute(removeLikes);
        jdbcTemplate.execute(removePosts);
        jdbcTemplate.execute(removeUsers);
    }
}
