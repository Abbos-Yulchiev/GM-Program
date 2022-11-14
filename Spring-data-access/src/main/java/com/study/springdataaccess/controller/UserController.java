package com.study.springdataaccess.controller;

import com.study.springdataaccess.entity.User;
import com.study.springdataaccess.entity.dto.UserDTO;
import com.study.springdataaccess.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getUserByEmail/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/getUsersByName")
    public List<User> getUsersByName(@RequestParam String name,
                                     @RequestParam(defaultValue = "20", required = false) Integer pageSize,
                                     @RequestParam(defaultValue = "0", required = false) Integer pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable long id, @RequestBody UserDTO userdto) {
        return userService.updateUser(id, userdto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}