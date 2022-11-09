package epam.com.JdbcAdvanced.service.impl;

import epam.com.JdbcAdvanced.model.Users;
import epam.com.JdbcAdvanced.model.dto.UserDTO;
import epam.com.JdbcAdvanced.repository.UserRepository;
import epam.com.JdbcAdvanced.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> getUserList(int pageSize, int pageNum) {
        return userRepository.getUserList(pageSize, pageNum);
    }

    @Override
    public String addUser(UserDTO userDTO) {
        return userRepository.addUser(userDTO);
    }

    @Override
    public String editUser(long userId, UserDTO userDTO) {
        return userRepository.editUser(userId, userDTO);
    }

    @Override
    public String deleteUser(long userId) {
        return userRepository.deleteUser(userId);
    }
}
