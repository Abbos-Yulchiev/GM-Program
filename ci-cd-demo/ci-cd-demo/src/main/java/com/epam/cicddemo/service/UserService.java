package com.epam.cicddemo.service;

import com.epam.cicddemo.exceptions.ResourceNotFoundException;
import com.epam.cicddemo.models.DTO.UserDTO;
import com.epam.cicddemo.models.User;
import com.epam.cicddemo.models.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    ResponseEntity<Page<User>> getUsers(Integer page, Integer size);

    ResponseEntity<ApiResponse<String>> addUser(UserDTO userDTO);

    ResponseEntity<ApiResponse<String>> editUser(Long id, UserDTO userDTO) throws ResourceNotFoundException;

    ResponseEntity<ApiResponse<String>> deleteUser(Long id) throws ResourceNotFoundException;
}
