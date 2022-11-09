package service.impl;

import entity.User;
import entity.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import repository.UserRepository;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> getUserById(Integer userId) {
        User user = userRepository.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Override
    public ResponseEntity<List<User>> getUsersByName(String name, int pageSize, int pageNum) {
        List<User> userList = userRepository.getUsersByName(name, pageSize, pageNum);
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @Override
    public ResponseEntity<String> createUser(UserDTO userDTO) {
        userRepository.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body("A new user created");
    }

    @Override
    public ResponseEntity<String> updateUser(int userId, UserDTO userdto) {
        userRepository.updateUser(userId, userdto);
        return ResponseEntity.status(HttpStatus.OK).body("User updated id=[" + userId + "]");
    }

    @Override
    public ResponseEntity<String> deleteUser(int userId) {
        userRepository.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted id=[" + userId + "]");

    }
}
