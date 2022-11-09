package epam.com.springbootmodule.controller;

import epam.com.springbootmodule.module.User;
import epam.com.springbootmodule.module.dto.LoginDTO;
import epam.com.springbootmodule.module.dto.UserDTO;
import epam.com.springbootmodule.response.Response;
import epam.com.springbootmodule.service.UserService;
import epam.com.springbootmodule.service.impl.AuthServiceImpl;
import io.micrometer.core.annotation.Timed;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController implements HealthIndicator {

    private final UserService userService;
    final AuthServiceImpl authService;


    public UserController(UserService userService, AuthServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Timed(value = "user.get.time", description = "time to retrieve users List", percentiles = {0.5, 0.9})
    @GetMapping("/userList")
    public List<User> getUsersList(@RequestParam(required = false, defaultValue = "0") int page,
                                   @RequestParam(required = false, defaultValue = "20") int pageSize) {
        return userService.getUsersList(page, pageSize);
    }

    @GetMapping("/{userId}")
    public Response<User> getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }


    @PostMapping("/addUser")
    public String addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }


    @PutMapping("/editUser/{userId}")
    public String editUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return userService.editUser(userId, userDTO);
    }


    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Health health() {
        List<User> userList = getUsersList(0, 5);
        if (!userList.isEmpty())
            return Health.up().withDetail("/address/addressList endpoint is working", "Available").build();
        else return Health.down().withDetail("/address/addressList endpoint is not working", "Unavailable").build();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}
