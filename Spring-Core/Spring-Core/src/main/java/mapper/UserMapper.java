package mapper;

import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setUsername(resultSet.getString(2));
        user.setEmail(resultSet.getString(3));
        return user;
    }
}
