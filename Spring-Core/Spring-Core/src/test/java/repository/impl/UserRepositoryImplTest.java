package repository.impl;

import entity.User;
import entity.dto.UserDTO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(locations = {"/spring.xml"})
@RunWith(SpringRunner.class)
public class UserRepositoryImplTest {

    @Mock
    @Autowired
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;


    @Test
    public void testConstructor() {
        new UserRepositoryImpl(mock(JdbcTemplate.class));
    }

    @Test
    public void testGetUsersByName() {
        assertTrue(userRepositoryImpl.getUsersByName("Name", 3, 10).isEmpty());
    }


    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("user.username2@email.org");
        userDTO.setId(6);
        userDTO.setUsername("user");
        userRepositoryImpl.createUser(userDTO);
    }


    @Test
    public void testCreateUser2() {
        UserDTO userDTO = mock(UserDTO.class);
        when(userDTO.getId()).thenReturn(-1);
        when(userDTO.getEmail()).thenReturn("user.username@email.org");
        when(userDTO.getUsername()).thenReturn("user");
        doNothing().when(userDTO).setEmail((String) any());
        doNothing().when(userDTO).setId(anyInt());
        doNothing().when(userDTO).setUsername((String) any());
        userDTO.setEmail("user.username@email.org");
        userDTO.setId(1);
        userDTO.setUsername("user");
        userRepositoryImpl.createUser(userDTO);
        verify(userDTO).getId();
        verify(userDTO).getEmail();
        verify(userDTO).getUsername();
        verify(userDTO).setEmail((String) any());
        verify(userDTO).setId(anyInt());
        verify(userDTO).setUsername((String) any());
    }


    @Test
    public void testUpdateUser() throws DataAccessException {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doNothing().when(jdbcTemplate).execute((String) any());
        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl(jdbcTemplate);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("user.username@email.org");
        userDTO.setId(1);
        userDTO.setUsername("user");
        userRepositoryImpl.updateUser(123, userDTO);
        verify(jdbcTemplate).execute((String) any());
    }


    @Test
    public void testUpdateUser2() throws DataAccessException {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doNothing().when(jdbcTemplate).execute((String) any());
        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl(jdbcTemplate);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(null);
        userDTO.setId(1);
        userDTO.setUsername("user");
        userRepositoryImpl.updateUser(123, userDTO);
        verify(jdbcTemplate).execute((String) any());
    }


    @Test
    public void testDeleteUser() throws DataAccessException {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        doNothing().when(jdbcTemplate).execute((String) any());
        (new UserRepositoryImpl(jdbcTemplate)).deleteUser(123);
        verify(jdbcTemplate).execute((String) any());
    }
}

