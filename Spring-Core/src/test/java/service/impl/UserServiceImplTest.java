package service.impl;

import entity.User;
import entity.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(locations = {"/spring.xml"})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Mock
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    void testConstructor() {
        new UserServiceImpl(new UserRepositoryImpl(mock(JdbcTemplate.class)));
    }


    @Test
    void testGetUsersByName() {
        ResponseEntity<List<User>> actualUsersByName = userServiceImpl.getUsersByName("Name", 3, 10);
        assertTrue(actualUsersByName.hasBody());
        assertEquals(HttpStatus.OK, actualUsersByName.getStatusCode());
        assertTrue(actualUsersByName.getHeaders().isEmpty());
    }


    @Test
    void testUpdateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setId(1);
        userDTO.setUsername("janedoe");
        ResponseEntity<String> actualUpdateUserResult = userServiceImpl.updateUser(123, userDTO);
        assertEquals("User updated id=[123]", actualUpdateUserResult.getBody());
        assertEquals(HttpStatus.OK, actualUpdateUserResult.getStatusCode());
        assertTrue(actualUpdateUserResult.getHeaders().isEmpty());
    }


    @Test
    void testDeleteUser() {
        ResponseEntity<String> actualDeleteUserResult = userServiceImpl.deleteUser(123);
        assertEquals("User deleted id=[123]", actualDeleteUserResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteUserResult.getStatusCode());
        assertTrue(actualDeleteUserResult.getHeaders().isEmpty());
    }
}

