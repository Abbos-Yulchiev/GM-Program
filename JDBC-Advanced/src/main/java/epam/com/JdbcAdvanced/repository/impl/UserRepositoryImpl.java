package epam.com.JdbcAdvanced.repository.impl;

import epam.com.JdbcAdvanced.model.Users;
import epam.com.JdbcAdvanced.model.dto.UserDTO;
import epam.com.JdbcAdvanced.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper mapper;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, ModelMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<Users> getUserList(int pageSize, int pageNum) {

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM users ORDER BY id LIMIT "
                + pageSize + " OFFSET " + pageNum * pageSize);
        return new ArrayList<>();
    }

    @Override
    public String addUser(UserDTO userDTO) {

        String sql = "INSERT INTO users (id, name, surname, birthdate) values (" +
                userDTO.getId() + ", '" +
                userDTO.getName() + "', '" +
                userDTO.getSurname() + "', '" +
                userDTO.getBirthday() + "')";
        jdbcTemplate.execute(sql);
        return "New user saved";
    }

    @Override
    public String editUser(long userId, UserDTO userDTO) {

        String name = userDTO.getName() == null ? "" : " name='" + userDTO.getName() + "',";
        String surname = userDTO.getSurname() == null ? "" : " surname='" + userDTO.getSurname() + "',";
        String birthdate = userDTO.getBirthday() == null ? "" : " birthdate='" + userDTO.getBirthday() + "'";
        String sql = "UPDATE users SET " + name + surname + birthdate + " WHERE id=" + userId;
        jdbcTemplate.execute(sql);
        return "User edited";
    }

    @Override
    public String deleteUser(long userId) {
        String sql = "DELETE FROM users WHERE id = " + userId;
        jdbcTemplate.execute(sql);
        return "User deleted with id [" + userId + "]";
    }

    @Override
    public Users addUserByProcedure(UserDTO userDTO) {

        String sql = """
                CREATE OR REPLACE PROCEDURE\s
                add_user(bigint, character varying, character varying, timestamp without time zone)
                LANGUAGE 'plpgsql'
                AS $$
                                
                BEGIN
                INSERT INTO public.users(id, name, surname, birthdate) values($1, $2, $3, $4);
                                
                COMMIT;
                                
                END;
                $$;
                """;

        jdbcTemplate.execute(sql);

        StringBuilder sql2 = new StringBuilder("CALL add_user(");
        sql2.append(userDTO.getId())
                .append(", '")
                .append(userDTO.getName())
                .append("', '")
                .append(userDTO.getSurname())
                .append("', '")
                .append(userDTO.getBirthday())
                .append("')");

        jdbcTemplate.execute(sql2.toString());

        Users users = new Users();
        mapper.map(userDTO, users);
        return users;
    }
}
