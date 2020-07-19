package address.geolocation.com.geo.usecase;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.database.AddressGateway;
import address.geolocation.com.geo.gateway.exception.AddAddressException;
import address.geolocation.com.geo.gateway.exception.AddressNotFoundException;
import address.geolocation.com.geo.gateway.exception.SearchAddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddAddressUseCase {
    private static final String ADDRESS_NOT_FOUND_MESSAGE = "endereço não encontrado";
    private static final Logger LOGGER = LoggerFactory.getLogger(AddAddressUseCase.class);

    private final AddressGateway addressGateway;

    @Autowired
    public AddAddressUseCase(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public AddressDomain execute(AddressDomain addressDomain) throws AddAddressException {
        try {
            LOGGER.info("Registrando endereço {}", addressDomain);
            return addressGateway.add(addressDomain);

        } catch (Exception ex) {
            LOGGER.error("Erro ao registrar endereço {}", addressDomain, ex);
            throw new AddAddressException("Problemas ao salvar endereço");
        }

    }

}
