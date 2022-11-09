package epam.com.springtesting.controller;

import epam.com.springtesting.entity.User;
import epam.com.springtesting.entity.dto.UserDTO;
import epam.com.springtesting.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * Done
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getUserByEmail")
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/getUsersByName")
    public User getUserByUsername(@RequestParam String username) {
        return userService.getUsersByName(username);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable int id, @RequestBody UserDTO userdto) {
        return userService.updateUser(id, userdto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
/**
 * List<User> getUsersByName(String name, int pageSize, int pageNum);
 * User getUserByEmail(String email);
 */