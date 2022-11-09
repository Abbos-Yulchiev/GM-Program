package epam.com.JdbcAdvanced.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import epam.com.JdbcAdvanced.model.dto.UserDTO;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserRepositoryImpl.class})
@ExtendWith(SpringExtension.class)
class UserRepositoryImplTest {
    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;


    @Test
    void testGetUserList() throws DataAccessException {
        when(jdbcTemplate.queryForList((String) any())).thenReturn(new ArrayList<>());
        assertTrue(userRepositoryImpl.getUserList(3, 10).isEmpty());
        verify(jdbcTemplate).queryForList((String) any());
    }


    @Test
    void testAddUser() throws DataAccessException {
        doNothing().when(jdbcTemplate).execute((String) any());

        UserDTO userDTO = new UserDTO();
        userDTO.setBirthday("2022-02-12 20:20:20.000000");
        userDTO.setId(123L);
        userDTO.setName("James");
        userDTO.setSurname("Lebrone");
        assertEquals("New user saved", userRepositoryImpl.addUser(userDTO));
        verify(jdbcTemplate).execute((String) any());
    }


    @Test
    void testEditUser() throws DataAccessException {
        doNothing().when(jdbcTemplate).execute((String) any());

        UserDTO userDTO = new UserDTO();
        userDTO.setBirthday("2022-02-12 20:20:20.000000");
        userDTO.setId(123L);
        userDTO.setName("James");
        userDTO.setSurname("Lebrone");
        assertEquals("User edited", userRepositoryImpl.editUser(123L, userDTO));
        verify(jdbcTemplate).execute((String) any());
    }



    @Test
    void testDeleteUser() throws DataAccessException {
        doNothing().when(jdbcTemplate).execute((String) any());
        assertEquals("User deleted with id [123]", userRepositoryImpl.deleteUser(123L));
        verify(jdbcTemplate).execute((String) any());
    }
}

