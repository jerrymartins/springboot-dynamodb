package address.geolocation.com.geo.usecase;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.database.AddressGateway;
import address.geolocation.com.geo.gateway.exception.AddressNotFoundException;
import address.geolocation.com.geo.gateway.exception.SearchAddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SearchAddressByZipCodeUseCase {
    private static final String ADDRESS_NOT_FOUND_MESSAGE = "endereço não encontrado";
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchAddressByZipCodeUseCase.class);

    private final AddressGateway addressGateway;

    @Autowired
    public SearchAddressByZipCodeUseCase(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public Set<AddressDomain> execute(String zipcode) throws AddressNotFoundException {
        try {
            return addressGateway.findByZipCode(zipcode);
        } catch (SearchAddressException ex ) {
            throw new AddressNotFoundException(ADDRESS_NOT_FOUND_MESSAGE);
        }
    }

}
