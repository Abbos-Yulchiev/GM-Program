package epam.com.springbootmodule.service;

import epam.com.springbootmodule.module.User;
import epam.com.springbootmodule.module.dto.UserDTO;
import epam.com.springbootmodule.response.Response;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    Response<User> getUserById(Long userId);

    List<User> getUsersList(int page,  int pageSize);

    String addUser(UserDTO userDTO);

    String editUser(Long usedId, UserDTO userDTO);

    String deleteUser(Long userId);
}
