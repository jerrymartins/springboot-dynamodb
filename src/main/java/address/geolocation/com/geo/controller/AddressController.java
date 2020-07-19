package address.geolocation.com.geo.controller;
import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.exception.AddAddressException;
import address.geolocation.com.geo.gateway.exception.AddressNotFoundException;
import address.geolocation.com.geo.gateway.exception.UpdateAddressException;
import address.geolocation.com.geo.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("address")
public class AddressController {

    private final SearchAddressByZipCodeUseCase searchAddressByZipCodeUseCase;
    private final SearchAddressesByCityUseCase searchAddressesByCityUseCase;
    private final SearchAddressesByIdUseCase searchAddressesByIdUseCase;
    private final AddAddressUseCase addAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;

    @Autowired
    public AddressController(SearchAddressByZipCodeUseCase searchAddressByZipCodeUseCase, SearchAddressesByCityUseCase searchAddressesByCityUseCase, SearchAddressesByIdUseCase searchAddressesByIdUseCase, AddAddressUseCase addAddressUseCase, UpdateAddressUseCase updateAddressUseCase) {
        this.searchAddressByZipCodeUseCase = searchAddressByZipCodeUseCase;
        this.searchAddressesByCityUseCase = searchAddressesByCityUseCase;
        this.searchAddressesByIdUseCase = searchAddressesByIdUseCase;
        this.addAddressUseCase = addAddressUseCase;
        this.updateAddressUseCase = updateAddressUseCase;
    }

    @GetMapping(path = "id/{id}")
    public AddressDomain findById(@PathVariable String id) throws AddressNotFoundException {
        return searchAddressesByIdUseCase.execute(id);
    }

    @GetMapping(path = "zipcode/{zipcode}")
    public Set<AddressDomain> findByZipCode(@PathVariable String zipcode) throws AddressNotFoundException {
        return searchAddressByZipCodeUseCase.execute(zipcode);
    }

    @GetMapping(path = "city/{city}")
    public Set<AddressDomain> findByCity(@PathVariable String city) throws AddressNotFoundException {
        return searchAddressesByCityUseCase.execute(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDomain create(@RequestBody @Valid AddressDomain addressDomain) throws AddAddressException {
        return addAddressUseCase.execute(addressDomain);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @Valid @RequestBody AddressDomain addressDomain) throws UpdateAddressException {
        addressDomain.setId(id);
        updateAddressUseCase.execute(addressDomain);
    }
}
