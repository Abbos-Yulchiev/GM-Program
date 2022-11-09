package service;

import entity.User;
import entity.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<User> getUserById(Integer id);

    ResponseEntity<User> getUserByEmail(String email);

    ResponseEntity<List<User>> getUsersByName(String name, int pageSize, int pageNum);

    ResponseEntity<String> createUser(UserDTO userDTO);

    ResponseEntity<String> updateUser(int userId, UserDTO userdto);

    ResponseEntity<String> deleteUser(int id);
}
