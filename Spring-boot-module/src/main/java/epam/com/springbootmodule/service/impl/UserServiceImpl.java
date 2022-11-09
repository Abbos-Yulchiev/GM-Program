package epam.com.springbootmodule.service.impl;

import epam.com.springbootmodule.exception.NotFoundException;
import epam.com.springbootmodule.module.Address;
import epam.com.springbootmodule.module.User;
import epam.com.springbootmodule.module.dto.UserDTO;
import epam.com.springbootmodule.repository.AddressRepository;
import epam.com.springbootmodule.repository.UserRepository;
import epam.com.springbootmodule.response.Response;
import epam.com.springbootmodule.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, HealthIndicator, UserDetailsService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }


    @Override
    public Response<User> getUserById(Long userId) {
        return new Response<>(userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: [" + userId + "]")));
    }

    @Override
    public List<User> getUsersList(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }

    @Override
    public String addUser(UserDTO userDTO) {
        Address address = addressRepository.findById(userDTO.getAddress())
                .orElseThrow(() -> new NotFoundException("Address not found with id [" + userDTO.getAddress() + "]"));
        User user = mapper.map(userDTO, User.class);
        user.setAddress(address);
        User save = userRepository.save(user);
        return "User saved id:[" + save.getId() + "]";
    }

    @Override
    public String editUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id [" + userId + "]"));
        Address address = addressRepository.findById(userDTO.getAddress())
                .orElseThrow(() -> new NotFoundException("Address not found with id [" + userDTO.getAddress() + "]"));
        mapper.map(userDTO, user);
        user.setAddress(address);
        User save = userRepository.save(user);
        return "User edited id:[" + save.getId() + "]";
    }

    @Override
    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "User deleted id:[" + userId + "]";
    }

    @Override
    public Health health() {
        Response<User> userById = getUserById(1L);
        User user = userById.getObject();
        if (user != null)
            return Health.up().withDetail("Getting an User by given id method is working", "Available").build();
        else
            return Health.down().withDetail("Getting an User by given id method is not working", "Unavailable").build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
