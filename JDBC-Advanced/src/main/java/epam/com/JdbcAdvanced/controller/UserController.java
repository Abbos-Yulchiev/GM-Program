package epam.com.JdbcAdvanced.controller;

import epam.com.JdbcAdvanced.model.Users;
import epam.com.JdbcAdvanced.model.dto.UserDTO;
import epam.com.JdbcAdvanced.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getUserList(@RequestParam(defaultValue = "0", required = false) int pageSize,
                                   @RequestParam(defaultValue = "20", required = false) int pageNum) {
        return userService.getUserList(pageSize, pageNum);
    }

    @PostMapping
    public String addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/{userId}")
    public String editUser(@PathVariable long userId, @RequestBody UserDTO userDTO) {
        return userService.editUser(userId, userDTO);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable long userId){
        return userService.deleteUser(userId);
    }

}
