package epam.com.springbootmodule.service.impl;

import epam.com.springbootmodule.exception.NotFoundException;
import epam.com.springbootmodule.module.Address;
import epam.com.springbootmodule.module.User;
import epam.com.springbootmodule.module.dto.UserDTO;
import epam.com.springbootmodule.repository.AddressRepository;
import epam.com.springbootmodule.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private AddressRepository addressRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    Address address = new Address();
    User user = new User();
    Address address1 = new Address();
    User user1 = new User();
    UserDTO userDTO = new UserDTO();


    @BeforeEach
    public void initEach() {
        address.setCity("Oxford");
        address.setHomeNumber("42");
        address.setId(123L);
        address.setStreet("Street");

        user.setAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("password");
        user.setPhoneNumber("4105551212");

        address1.setCity("Oxford");
        address1.setHomeNumber("42");
        address1.setId(123L);
        address1.setStreet("Street");

        user1.setAddress(address1);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("password");
        user1.setPhoneNumber("4105551212");

        userDTO.setAddress(1L);
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFirstName("Jane");
        userDTO.setLastName("Doe");
        userDTO.setPassword("password");
        userDTO.setPhoneNumber("4105551212");
    }


    @Test
    void testGetUserById() {

        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(any())).thenReturn(ofResult);
        assertSame(user, userServiceImpl.getUserById(123L).getObject());
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
    void testGetUsersList() {
        when(userRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(userServiceImpl.getUsersList(1, 3).isEmpty());
        verify(userRepository).findAll((Pageable) any());
    }


    @Test
    void testGetUsersList4() {
        when(userRepository.findAll((Pageable) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userServiceImpl.getUsersList(1, 3));
        verify(userRepository).findAll((Pageable) any());
    }


    @Test
    void testAddUser() {

        when(userRepository.save(any())).thenReturn(user);

        Optional<Address> ofResult = Optional.of(address);
        when(addressRepository.findById(any())).thenReturn(ofResult);

        when(modelMapper.map(any(), any())).thenReturn(user);

        assertEquals("User saved id:[123]", userServiceImpl.addUser(userDTO));
        verify(userRepository).save(any());
        verify(addressRepository).findById(any());
        verify(modelMapper).map(any(), any());
    }


    @Test
    void testAddUser2() {

        when(userRepository.save(any())).thenThrow(new NotFoundException("An error occurred"));
        Optional<Address> ofResult = Optional.of(address);
        when(addressRepository.findById(any())).thenReturn(ofResult);

        when(modelMapper.map(any(), any())).thenReturn(user);

        assertThrows(NotFoundException.class, () -> userServiceImpl.addUser(userDTO));
        verify(userRepository).save(any());
        verify(addressRepository).findById(any());
        verify(modelMapper).map(any(), any());
    }


    @Test
    void testAddUser3() {

        when(userRepository.save(any())).thenReturn(user);
        when(addressRepository.findById(any())).thenReturn(Optional.empty());

        when(modelMapper.map(any(), any())).thenReturn("Map");
        when(modelMapper.map(any(), any())).thenReturn(user1);

        assertThrows(NotFoundException.class, () -> userServiceImpl.addUser(userDTO));
        verify(addressRepository).findById(any());
    }


    @Test
    void testEditUser() {

        Optional<User> ofResult = Optional.of(user);

        when(userRepository.save(any())).thenReturn(user1);
        when(userRepository.findById(any())).thenReturn(ofResult);

        Optional<Address> ofResult1 = Optional.of(address1);
        when(addressRepository.findById(any())).thenReturn(ofResult1);

        assertEquals("User edited id:[123]", userServiceImpl.editUser(123L, userDTO));
        verify(userRepository).save(any());
        verify(userRepository).findById(any());
        verify(addressRepository).findById(any());
    }


    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(any());
        assertEquals("User deleted id:[123]", userServiceImpl.deleteUser(123L));
        verify(userRepository).deleteById(any());
    }


    @Test
    void testDeleteUser2() {
        doThrow(new NotFoundException("An error occurred")).when(userRepository).deleteById(any());
        assertThrows(NotFoundException.class, () -> userServiceImpl.deleteUser(123L));
        verify(userRepository).deleteById(any());
    }
}

