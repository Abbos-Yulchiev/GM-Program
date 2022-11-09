package repository.impl;

import entity.User;
import entity.dto.UserDTO;
import mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int userId) {

        String sql = "SELECT * FROM users WHERE id = " + userId;
        return jdbcTemplate.queryForObject(sql, new UserMapper());
    }

    @Override
    public User getUserByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = " + email;
        return jdbcTemplate.queryForObject(sql, new UserMapper());
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {

        List<Map<String, Object>> usersMap = jdbcTemplate.queryForList("SELECT * FROM users " +
                "WHERE username = '" + name + "' ORDER BY id LIMIT " + pageSize + " OFFSET " + pageNum * pageSize);
        List<User> userList = new ArrayList<>();
        for (Map<String, Object> map : usersMap) {
            User user = new User();
            user.setId((Integer) map.get("id"));
            user.setUsername((String) map.get("username"));
            user.setEmail((String) map.get("email"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        String sql = "INSERT INTO users (id, username, email) values ("
                + userDTO.getId() + ", '" + userDTO.getUsername() + "', '"
                + userDTO.getEmail() + "')";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void updateUser(int userId, UserDTO userDTO) {
        String username = userDTO.getUsername() == null ? "" : " username='" + userDTO.getUsername() + "'";
        String email = userDTO.getEmail() == null ? "" : " email='" + userDTO.getEmail() + "'";
        if (!username.equals("") && !email.equals("")) username += ",";
        String sql = "UPDATE users SET " + username + email + " WHERE id=" + userId;
        jdbcTemplate.execute(sql);
    }

    @Override
    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id=" + userId;
        jdbcTemplate.execute(sql);
    }
}
