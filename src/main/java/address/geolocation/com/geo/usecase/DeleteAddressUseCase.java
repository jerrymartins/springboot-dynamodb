package address.geolocation.com.geo.usecase;

import address.geolocation.com.geo.gateway.database.AddressGateway;
import address.geolocation.com.geo.gateway.exception.DeleteAddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteAddressUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteAddressUseCase.class);

    private final AddressGateway addressGateway;

    @Autowired
    public DeleteAddressUseCase(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public void execute(String id) throws DeleteAddressException {
        try {
            addressGateway.delete(id);

        } catch (Exception ex) {
            throw new DeleteAddressException("Problemas ao deletar endereço");
        }

    }

}
