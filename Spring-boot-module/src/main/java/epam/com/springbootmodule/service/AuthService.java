package epam.com.springbootmodule.service;


import epam.com.springbootmodule.module.User;
import epam.com.springbootmodule.module.dto.LoginDTO;

public interface AuthService {

    Object login(LoginDTO loginDTO);

    Object authToken(User user);
}
