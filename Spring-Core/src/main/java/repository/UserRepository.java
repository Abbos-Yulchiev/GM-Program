package repository;

import entity.User;
import entity.dto.UserDTO;

import java.util.List;

public interface UserRepository {

    User getUserById(int userId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name, int pageSize, int pageNum);

    void createUser(UserDTO userDTO);

    void updateUser(int userId, UserDTO userDTO);

    void deleteUser(int userId);
}
