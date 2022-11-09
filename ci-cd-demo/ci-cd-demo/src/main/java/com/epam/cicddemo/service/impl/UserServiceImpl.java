package com.epam.cicddemo.service.impl;

import com.epam.cicddemo.exceptions.ResourceNotFoundException;
import com.epam.cicddemo.models.DTO.UserDTO;
import com.epam.cicddemo.models.User;
import com.epam.cicddemo.models.response.ApiResponse;
import com.epam.cicddemo.repository.UserRepository;
import com.epam.cicddemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Page<User>> getUsers(Integer page, Integer size) {


        Pageable pageable = PageRequest.of(page, size);
        Page<User> userList = userRepository.findAll(pageable);
        logger.info(userList.toString());
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @Override
    public ResponseEntity<ApiResponse<String>> addUser(UserDTO userDTO) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>("A new User saved", true));
    }

    @Override
    public ResponseEntity<ApiResponse<String>> editUser(Long id, UserDTO userDTO) throws ResourceNotFoundException {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: [" + id + "]"));
        (new ModelMapper()).map(userDTO, user);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>("User edited with id: [" + id + "]", true));
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteUser(Long id) throws ResourceNotFoundException {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>("User not found id: [" + id + "]", true));
        else {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("User deleted id: [" + id + "]", true));
        }
    }
}
