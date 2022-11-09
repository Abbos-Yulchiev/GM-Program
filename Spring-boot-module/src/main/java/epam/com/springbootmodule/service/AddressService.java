package epam.com.springbootmodule.service;

import epam.com.springbootmodule.module.Address;
import epam.com.springbootmodule.module.dto.AddressDTO;
import epam.com.springbootmodule.response.Response;

import java.util.List;

public interface AddressService {

    List<Address> getAddressList(int page, int pageSize);

    Response<Address> getAddressById(Long addressId);

    String addAddress(AddressDTO addressDTO);

    String editAddress(Long addressId, AddressDTO addressDTO);

    String deleteAddress(Long addressId);
}
