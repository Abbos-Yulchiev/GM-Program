package epam.com.springbootmodule.service.impl;

import epam.com.springbootmodule.exception.NotFoundException;
import epam.com.springbootmodule.module.Address;
import epam.com.springbootmodule.module.dto.AddressDTO;
import epam.com.springbootmodule.repository.AddressRepository;
import epam.com.springbootmodule.response.Response;
import epam.com.springbootmodule.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService, HealthIndicator {

    private final AddressRepository addressRepository;
    private final ModelMapper mapper;


    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Address> getAddressList(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return addressRepository.findAll(pageable).getContent();
    }

    @Override
    public Response<Address> getAddressById(Long addressId) {
        return new Response<>(addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found with id: [" + addressId + "]")));
    }

    @Override
    public String addAddress(AddressDTO addressDTO) {
        Address address = mapper.map(addressDTO, Address.class);
        Address save = addressRepository.save(address);
        return "Address saved id:[" + save.getId() + "]";
    }

    @Override
    public String editAddress(Long addressId, AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found", addressId));
        mapper.map(addressDTO, address);
        Address save = addressRepository.save(address);
        return "Address edited id:[" + save.getId() + "]";
    }

    @Override
    public String deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
        return "Address deleted id:[" + addressId + "]";
    }

    @Override
    public Health health() {
        Response<Address> addressById = getAddressById(1L);
        Address address = addressById.getObject();
        if (address != null)
            return Health.up().withDetail("Getting address by id number is working", "Available").build();
        else
            return Health.down().withDetail("Getting address by id number is not working", "Unavailable").build();
    }
}
