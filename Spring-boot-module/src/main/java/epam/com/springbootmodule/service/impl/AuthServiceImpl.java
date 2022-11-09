package epam.com.springbootmodule.service.impl;

import epam.com.springbootmodule.module.Role;
import epam.com.springbootmodule.module.User;
import epam.com.springbootmodule.module.UserInfo;
import epam.com.springbootmodule.module.dto.LoginDTO;
import epam.com.springbootmodule.repository.UserRepository;
import epam.com.springbootmodule.security.JwtProvider;
import epam.com.springbootmodule.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public String login(LoginDTO loginDTO) {

        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(),
                    loginDTO.getPassword()
            ));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getUsername());
            if (user.isAccountNonLocked()
                    && user.isAccountNonExpired()
                    && user.isCredentialsNonExpired()) {
                return token;
            } else {
                return "User expired";
            }
        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("Username or password is wrong!");
        }
    }

    @Override
    public String authToken(User user) {
        if (user == null) {
            return "Unauthorized";
        }
        UserInfo userInfo = UserInfo.builder()
                .username(user.getUsername())
                .roles(user.getRoles()).build();
        return "Success!";
    }


    private List<String> getRoleList(Set<Role> roles) {
        List<String> roleList = new ArrayList<>();
        for (Role role : roles) {
            roleList.add(role.getName());
        }
        return roleList;
    }
}
