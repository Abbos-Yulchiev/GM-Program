package com.study.springdataaccess.service.impl;

import com.study.springdataaccess.entity.User;
import com.study.springdataaccess.entity.dto.UserDTO;
import com.study.springdataaccess.exceptions.NotFoundException;
import com.study.springdataaccess.repository.UserRepository;
import com.study.springdataaccess.service.UserService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);


    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new
                NotFoundException("User not found with: id [" + userId + "]"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(() -> new
                NotFoundException("User not found with: email: [" + email + "]"));
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<User> users = userRepository.getUsersByName(name, pageable);
        LOGGER.info(users);
        return users.toList();
    }

    @Override
    public String createUser(UserDTO userDTO) {
        User user = new User();
        mapper.map(userDTO, user);
        User userSaved = userRepository.save(user);
        LOGGER.info("A new user created id is: [" + userSaved.getId() + "]");
        return "A new user created id is: [" + userSaved.getId() + "]";
    }

    @Override
    public String updateUser(long userId, UserDTO userdto) {

        User user = userRepository.findById(userId).orElseThrow(() -> new
                NotFoundException("User not found with: id [" + userId + "]"));
        mapper.map(userdto, user);
        userRepository.save(user);
        LOGGER.info("User updated id=[" + userId + "]");
        return "User updated id=[" + userId + "]";
    }

    @Override
    public String deleteUser(long userId) {
        userRepository.deleteById(userId);
        LOGGER.info("User deleted id=[" + userId + "]");
        return "User deleted id=[" + userId + "]";
    }
}
