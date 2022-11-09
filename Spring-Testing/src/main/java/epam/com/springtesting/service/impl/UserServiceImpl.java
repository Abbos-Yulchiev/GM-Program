package epam.com.springtesting.service.impl;

import epam.com.springtesting.entity.User;
import epam.com.springtesting.entity.dto.UserDTO;
import epam.com.springtesting.exceptions.NotFoundException;
import epam.com.springtesting.repository.UserRepository;
import epam.com.springtesting.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found! Id [" + id + "]"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new NotFoundException("User not found! Email [" + email + "]"));
    }

    @Override
    public User getUsersByName(String username) {
        return userRepository.findUserByUserName(username).orElseThrow(() ->
                new NotFoundException("User not found! Username [" + username + "]"));
    }

    @Override
    public String createUser(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return "New user created id [" + savedUser.getId() + "]";
    }

    @Override
    public String updateUser(int id, UserDTO userdto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found! Id [" + id + "]"));
        mapper.map(userdto, user);
        userRepository.save(user);
        return "User updated id [" + id + "]";
    }

    @Override
    public String deleteUser(int id) {
        userRepository.deleteById(id);
        return "User deleted id [" + id + "]";
    }
}
