package address.geolocation.com.geo.usecase;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.database.AddressGateway;
import address.geolocation.com.geo.gateway.exception.AddAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddAddressUseCase {
    private final AddressGateway addressGateway;

    @Autowired
    public AddAddressUseCase(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public AddressDomain execute(AddressDomain addressDomain) throws AddAddressException {
        try {
            return addressGateway.add(addressDomain);

        } catch (Exception ex) {
            throw new AddAddressException("Problemas ao salvar endereço");
        }

    }

}
