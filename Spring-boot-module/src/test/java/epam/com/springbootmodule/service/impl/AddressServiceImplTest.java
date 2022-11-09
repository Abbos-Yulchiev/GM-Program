package epam.com.springbootmodule.service.impl;

import epam.com.springbootmodule.exception.NotFoundException;
import epam.com.springbootmodule.module.Address;
import epam.com.springbootmodule.module.dto.AddressDTO;
import epam.com.springbootmodule.repository.AddressRepository;
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

@ContextConfiguration(classes = {AddressServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AddressServiceImplTest {

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    Address address = new Address();
    Address address1 = new Address();
    AddressDTO addressDTO = new AddressDTO();


    @BeforeEach
    public void initEach() {
        address.setCity("Oxford");
        address.setHomeNumber("42");
        address.setId(123L);
        address.setStreet("Street");

        address1.setCity("Oxford");
        address1.setHomeNumber("42");
        address1.setId(123L);
        address1.setStreet("Street");

        addressDTO.setCity("Oxford");
        addressDTO.setHomeNumber("42");
        addressDTO.setStreet("Street");
    }

    @Test
    void testGetAddressList() {
        when(addressRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(addressServiceImpl.getAddressList(1, 3).isEmpty());
        verify(addressRepository).findAll((Pageable) any());
    }


    @Test
    void testGetAddressList3() {
        when(addressRepository.findAll((Pageable) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> addressServiceImpl.getAddressList(1, 3));
        verify(addressRepository).findAll((Pageable) any());
    }


    @Test
    void testGetAddressById() {

        Optional<Address> ofResult = Optional.of(address);
        when(addressRepository.findById(any())).thenReturn(ofResult);
        assertSame(address, addressServiceImpl.getAddressById(123L).getObject());
        verify(addressRepository).findById(any());
    }


    @Test
    void testGetAddressById2() {
        when(addressRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> addressServiceImpl.getAddressById(123L));
        verify(addressRepository).findById(any());
    }


    @Test
    void testGetAddressById3() {
        when(addressRepository.findById(any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> addressServiceImpl.getAddressById(123L));
        verify(addressRepository).findById(any());
    }


    @Test
    void testAddAddress() {

        when(addressRepository.save(any())).thenReturn(address);
        when(modelMapper.map(any(), any())).thenReturn(address1);

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity("Oxford");
        addressDTO.setHomeNumber("42");
        addressDTO.setStreet("Street");
        assertEquals("Address saved id:[123]", addressServiceImpl.addAddress(addressDTO));
        verify(addressRepository).save(any());
        verify(modelMapper).map(any(), any());
    }


    @Test
    void testAddAddress2() {

        when(addressRepository.save(any())).thenReturn(address);
        when(modelMapper.map(any(), any()))
                .thenThrow(new NotFoundException("An error occurred"));

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity("Oxford");
        addressDTO.setHomeNumber("42");
        addressDTO.setStreet("Street");
        assertThrows(NotFoundException.class, () -> addressServiceImpl.addAddress(addressDTO));
        verify(modelMapper).map(any(), any());
    }


    @Test
    void testEditAddress() {

        Optional<Address> ofResult = Optional.of(address);

        when(addressRepository.save(any())).thenReturn(address1);
        when(addressRepository.findById(any())).thenReturn(ofResult);

        assertEquals("Address edited id:[123]", addressServiceImpl.editAddress(123L, addressDTO));
        verify(addressRepository).save(any());
        verify(addressRepository).findById(any());
    }


    @Test
    void testDeleteAddress() {
        doNothing().when(addressRepository).deleteById(any());
        assertEquals("Address deleted id:[123]", addressServiceImpl.deleteAddress(123L));
        verify(addressRepository).deleteById(any());
    }


    @Test
    void testDeleteAddress2() {
        doThrow(new NotFoundException("An error occurred")).when(addressRepository).deleteById(any());
        assertThrows(NotFoundException.class, () -> addressServiceImpl.deleteAddress(123L));
        verify(addressRepository).deleteById(any());
    }
}

