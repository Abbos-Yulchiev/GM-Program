package epam.com.springbootmodule.controller;

import epam.com.springbootmodule.module.Address;
import epam.com.springbootmodule.module.dto.AddressDTO;
import epam.com.springbootmodule.response.Response;
import epam.com.springbootmodule.service.AddressService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController implements HealthIndicator {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addressList")
    public List<Address> getAddressList(@RequestParam(required = false, defaultValue = "0") int page,
                                        @RequestParam(required = false, defaultValue = "20") int pageSize) {
        return addressService.getAddressList(page, pageSize);
    }

    @GetMapping("/{addressId}")
    public Response<Address> getAddressById(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }


    @PostMapping("/addAddress")
    public String addAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.addAddress(addressDTO);
    }

    @PutMapping("/editAddress/{addressId}")
    public String editAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO) {
        return addressService.editAddress(addressId, addressDTO);
    }

    @DeleteMapping("/delete/{addressId}")
    public String deleteAddress(@PathVariable Long addressId) {
        return addressService.deleteAddress(addressId);
    }


    @Override
    public Health health() {
        List<Address> addressList = getAddressList(0, 5);
        if (!addressList.isEmpty())
            return Health.up().withDetail("/address/addressList endpoint is working", "Available").build();
        else return Health.down().withDetail("/address/addressList endpoint is not working", "Unavailable").build();
    }
}
