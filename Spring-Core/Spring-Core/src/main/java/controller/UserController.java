package controller;

import entity.User;
import entity.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

/**
 * Done
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/getUsersByName")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name,
                                                     @RequestParam(defaultValue = "0", required = false) Integer pageSize,
                                                     @RequestParam(defaultValue = "20", required = false) Integer pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody UserDTO userdto) {
        return userService.updateUser(id, userdto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
/**
 * List<User> getUsersByName(String name, int pageSize, int pageNum);
 * User getUserByEmail(String email);
 */