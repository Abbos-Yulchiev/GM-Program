package com.study.springdataaccess.service.impl;

import com.study.springdataaccess.entity.User;
import com.study.springdataaccess.entity.dto.UserDTO;
import com.study.springdataaccess.exceptions.NotFoundException;
import com.study.springdataaccess.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Test
    void testGetUserById() {
        User user = new User();
        user.setEmail("alex.santiago@email.org");
        user.setId(123L);
        user.setUsername("alex-santiago");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(any())).thenReturn(ofResult);
        assertSame(user, userServiceImpl.getUserById(123L));
        verify(userRepository).findById(any());
    }

    @Test
    void testGetUserById2() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userServiceImpl.getUserById(123L));
        verify(userRepository).findById(any());
    }

    @Test
    void testGetUserById3() {
        when(userRepository.findById(any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userServiceImpl.getUserById(123L));
        verify(userRepository).findById(any());
    }

    @Test
    void testGetUserByEmail() {
        User user = new User();
        user.setEmail("alex.santiago@email.org");
        user.setId(123L);
        user.setUsername("alex-santiago");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.getUserByEmail(any())).thenReturn(ofResult);
        assertSame(user, userServiceImpl.getUserByEmail("alex.santiago@email.org"));
        verify(userRepository).getUserByEmail(any());
    }

    @Test
    void testGetUserByEmail2() {
        when(userRepository.getUserByEmail(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userServiceImpl.getUserByEmail("alex.santiago@email.org"));
        verify(userRepository).getUserByEmail(any());
    }

    @Test
    void testGetUserByEmail3() {
        when(userRepository.getUserByEmail(any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userServiceImpl.getUserByEmail("alex.santiago@email.org"));
        verify(userRepository).getUserByEmail(any());
    }

    @Test
    void testGetUsersByName() {
        when(userRepository.getUsersByName(any(), any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(userServiceImpl.getUsersByName("Name", 3, 10).isEmpty());
        verify(userRepository).getUsersByName(any(), any());
    }

    @Test
    void testGetUsersByName4() {
        when(userRepository.getUsersByName(any(), any()))
                .thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userServiceImpl.getUsersByName("Name", 3, 10));
        verify(userRepository).getUsersByName(any(), any());
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setEmail("alex.santiago@email.org");
        user.setId(123L);
        user.setUsername("alex-santiago");
        when(userRepository.save((User) any())).thenReturn(user);
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("alex.santiago@email.org");
        userDTO.setUsername("alex-santiago");
        assertEquals("A new user created id is: [123]", userServiceImpl.createUser(userDTO));
        verify(userRepository).save((User) any());
        verify(modelMapper).map((Object) any(), (Object) any());
    }

    @Test
    void testCreateUser2() {
        User user = new User();
        user.setEmail("alex.santiago@email.org");
        user.setId(123L);
        user.setUsername("alex-santiago");
        when(userRepository.save((User) any())).thenReturn(user);
        doThrow(new NotFoundException("An error occurred")).when(modelMapper).map((Object) any(), (Object) any());

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("alex.santiago@email.org");
        userDTO.setUsername("alex-santiago");
        assertThrows(NotFoundException.class, () -> userServiceImpl.createUser(userDTO));
        verify(modelMapper).map((Object) any(), (Object) any());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setEmail("alex.santiago@email.org");
        user.setId(123L);
        user.setUsername("alex-santiago");
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setEmail("alex.santiago@email.org");
        user1.setId(123L);
        user1.setUsername("alex-santiago");
        when(userRepository.save((User) any())).thenReturn(user1);
        when(userRepository.findById(any())).thenReturn(ofResult);
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("alex.santiago@email.org");
        userDTO.setUsername("alex-santiago");
        assertEquals("User updated id=[123]", userServiceImpl.updateUser(123L, userDTO));
        verify(userRepository).save((User) any());
        verify(userRepository).findById(any());
        verify(modelMapper).map((Object) any(), (Object) any());
    }


    @Test
    void testUpdateUser2() {
        User user = new User();
        user.setEmail("alex.santiago@email.org");
        user.setId(123L);
        user.setUsername("alex-santiago");
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setEmail("alex.santiago@email.org");
        user1.setId(123L);
        user1.setUsername("alex-santiago");
        when(userRepository.save((User) any())).thenReturn(user1);
        when(userRepository.findById(any())).thenReturn(ofResult);
        doThrow(new NotFoundException("An error occurred")).when(modelMapper).map((Object) any(), (Object) any());

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("alex.santiago@email.org");
        userDTO.setUsername("alex-santiago");
        assertThrows(NotFoundException.class, () -> userServiceImpl.updateUser(123L, userDTO));
        verify(userRepository).findById(any());
        verify(modelMapper).map((Object) any(), (Object) any());
    }

    @Test
    void testUpdateUser3() {
        User user = new User();
        user.setEmail("alex.santiago@email.org");
        user.setId(123L);
        user.setUsername("alex-santiago");
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("alex.santiago@email.org");
        userDTO.setUsername("alex-santiago");
        assertThrows(NotFoundException.class, () -> userServiceImpl.updateUser(123L, userDTO));
        verify(userRepository).findById(any());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(any());
        assertEquals("User deleted id=[123]", userServiceImpl.deleteUser(123L));
        verify(userRepository).deleteById(any());
    }

    @Test
    void testDeleteUser2() {
        doThrow(new NotFoundException("An error occurred")).when(userRepository).deleteById(any());
        assertThrows(NotFoundException.class, () -> userServiceImpl.deleteUser(123L));
        verify(userRepository).deleteById(any());
    }
}

