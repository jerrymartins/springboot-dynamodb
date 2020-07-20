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
public class SearchAddressesByCityUseCase {
    private static final String ADDRESS_NOT_FOUND_MESSAGE = "endereços não encontrados";
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchAddressesByCityUseCase.class);

    private final AddressGateway addressGateway;

    @Autowired
    public SearchAddressesByCityUseCase(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public Set<AddressDomain> execute(String city) throws AddressNotFoundException {
        try {
            return addressGateway.findByCity(city);
        } catch (SearchAddressException ex ) {
            throw new AddressNotFoundException(ADDRESS_NOT_FOUND_MESSAGE);
        }
    }

}
