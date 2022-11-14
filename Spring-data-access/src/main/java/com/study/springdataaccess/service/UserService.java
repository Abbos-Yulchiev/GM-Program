package com.study.springdataaccess.service;

import com.study.springdataaccess.entity.User;
import com.study.springdataaccess.entity.dto.UserDTO;

import java.util.List;

public interface UserService {

    User getUserById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name, int pageSize, int pageNum);

    String createUser(UserDTO userDTO);

    String updateUser(long userId, UserDTO userdto);

    String deleteUser(long id);
}
