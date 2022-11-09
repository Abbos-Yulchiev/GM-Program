package com.epam.cicddemo.controllers;

import com.epam.cicddemo.exceptions.ResourceNotFoundException;
import com.epam.cicddemo.models.DTO.UserDTO;
import com.epam.cicddemo.models.User;
import com.epam.cicddemo.models.response.ApiResponse;
import com.epam.cicddemo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<Page<User>> getUsers(@RequestParam(defaultValue = "0", required = false) Integer page,
                                               @RequestParam(defaultValue = "20", required = false) Integer size) {

        return userService.getUsers(page, size);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<String>> editUser(@PathVariable Long id, @RequestBody UserDTO userDTO) throws ResourceNotFoundException {
        return userService.editUser(id, userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
        return userService.deleteUser(id);
    }
}
