package epam.com.JdbcAdvanced.service;

import epam.com.JdbcAdvanced.model.Users;
import epam.com.JdbcAdvanced.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<Users> getUserList(int pageSize, int pageNum);

    String addUser(UserDTO userDTO);

    String editUser(long userId, UserDTO userDTO);

    String deleteUser(long userId);
}
