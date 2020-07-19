package address.geolocation.com.geo.usecase;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.database.AddressGateway;
import address.geolocation.com.geo.gateway.exception.AddressNotFoundException;
import address.geolocation.com.geo.gateway.exception.SearchAddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SearchAddressesByIdUseCase {
    private static final String ADDRESS_NOT_FOUND_MESSAGE = "endereço não encontrado";
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchAddressesByIdUseCase.class);

    private final AddressGateway addressGateway;

    @Autowired
    public SearchAddressesByIdUseCase(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public AddressDomain execute(String id) throws AddressNotFoundException {
        try {
            Optional<AddressDomain> addressDomainOptional = addressGateway.findById(id);
            if (!addressDomainOptional.isPresent()) {
                throw new AddressNotFoundException(ADDRESS_NOT_FOUND_MESSAGE);
            }
            return addressDomainOptional.get();
        } catch (SearchAddressException ex ) {
            throw new AddressNotFoundException(ADDRESS_NOT_FOUND_MESSAGE);
        }
    }

}
