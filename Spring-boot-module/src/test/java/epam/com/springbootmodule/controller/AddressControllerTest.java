package epam.com.springbootmodule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import epam.com.springbootmodule.module.Address;
import epam.com.springbootmodule.module.dto.AddressDTO;
import epam.com.springbootmodule.response.Response;
import epam.com.springbootmodule.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AddressController.class})
@ExtendWith(SpringExtension.class)
class AddressControllerTest {

    @Autowired
    private AddressController addressController;

    @MockBean
    private AddressService addressService;

    @Test
    void testGetAddressList() throws Exception {
        when(addressService.getAddressList(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address/addressList");
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetAddressList2() throws Exception {
        when(addressService.getAddressList(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/address/addressList");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetAddressById() throws Exception {
        when(addressService.getAddressById(any())).thenReturn(new Response<>(new Address()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address/{addressId}", 123L);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"object\":{\"id\":null,\"homeNumber\":null,\"street\":null,\"city\":null}}"));
    }


    @Test
    void testAddAddress() throws Exception {
        when(addressService.addAddress(any())).thenReturn("42 Main St");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity("Oxford");
        addressDTO.setHomeNumber("42");
        addressDTO.setStreet("Street");
        String content = (new ObjectMapper()).writeValueAsString(addressDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/address/addAddress")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42 Main St"));
    }

    @Test
    void testEditAddress() throws Exception {
        when(addressService.editAddress(any(), any())).thenReturn("42 Main St");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity("Oxford");
        addressDTO.setHomeNumber("42");
        addressDTO.setStreet("Street");
        String content = (new ObjectMapper()).writeValueAsString(addressDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/address/editAddress/{addressId}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42 Main St"));
    }

    @Test
    void testDeleteAddress() throws Exception {
        when(addressService.deleteAddress(any())).thenReturn("42 Main St");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/address/delete/{addressId}", 123L);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42 Main St"));
    }


    @Test
    void testDeleteAddress2() throws Exception {
        when(addressService.deleteAddress(any())).thenReturn("42 Main St");
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/address/delete/{addressId}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42 Main St"));
    }
}

