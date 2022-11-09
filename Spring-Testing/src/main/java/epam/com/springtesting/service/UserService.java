package epam.com.springtesting.service;

import epam.com.springtesting.entity.User;
import epam.com.springtesting.entity.dto.UserDTO;

public interface UserService {

    User getUserById(int id);

    User getUserByEmail(String email);

    User getUsersByName(String username);

    String createUser(UserDTO userDTO);

    String updateUser(int id, UserDTO userdto);

    String deleteUser(int id);
}
