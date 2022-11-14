package com.study.springdataaccess.controller;

import com.study.springdataaccess.entity.UserAccount;
import com.study.springdataaccess.entity.dto.UserAccountDTO;
import com.study.springdataaccess.service.UserAccountService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/userAccount")
public class UserAccountController {

    private final UserAccountService userService;

    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUserAccountInfo/{userId}")
    public UserAccount getUserById(@PathVariable long userId) {
        return userService.getUserAccountInfo(userId);
    }


    @PostMapping("/createUserAccount")
    public String createUserAccount(@RequestBody UserAccountDTO accountDTO) {
        return userService.createUserAccount(accountDTO);
    }

    @PutMapping("/updateUserAccount/{id}")
    public String updateUserAccount(@PathVariable long id, @RequestBody UserAccountDTO accountDTO) {
        return userService.updateUserAccount(id, accountDTO);
    }

    @DeleteMapping("/deleteUserAccount/{id}")
    public String deleteUserAccount(@PathVariable long id) {
        return userService.deleteUserAccount(id);
    }
}