package epam.com.JdbcAdvanced.repository;

import epam.com.JdbcAdvanced.model.Users;
import epam.com.JdbcAdvanced.model.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {

    List<Users> getUserList(int pageSize, int pageNum);

    String addUser(UserDTO userDTO);

    String editUser(long userId, UserDTO userDTO);

    String deleteUser(long userId);
}
