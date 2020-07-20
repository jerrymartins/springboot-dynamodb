package address.geolocation.com.geo.usecase;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.domain.GeoLocationDomain;
import address.geolocation.com.geo.gateway.database.AddressGateway;
import address.geolocation.com.geo.gateway.exception.UpdateAddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UpdateAddressUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateAddressUseCase.class);
    private final GetGeolocationDataUseCase getGeolocationDataUseCase;

    private final AddressGateway addressGateway;

    @Autowired
    public UpdateAddressUseCase(GetGeolocationDataUseCase getGeolocationDataUseCase, AddressGateway addressGateway) {
        this.getGeolocationDataUseCase = getGeolocationDataUseCase;
        this.addressGateway = addressGateway;
    }

    public void execute(AddressDomain addressDomain) throws UpdateAddressException {
        try {
            if (StringUtils.isEmpty(addressDomain.getLatitude())) {
                LOGGER.info("buscando dados de latitude e longitude");
                GeoLocationDomain geodata = getGeolocationDataUseCase.execute(addressDomain);
                addressDomain.setLatitude(geodata.getLatitude());
                addressDomain.setLongitude(geodata.longitude);
            }
            addressGateway.update(addressDomain);

        } catch (Exception ex) {
            throw new UpdateAddressException("Problemas ao atualizar endereço");
        }

    }

}
