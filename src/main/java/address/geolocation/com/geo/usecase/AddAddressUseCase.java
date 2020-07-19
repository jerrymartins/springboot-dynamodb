package address.geolocation.com.geo.usecase;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.domain.GeoLocationDomain;
import address.geolocation.com.geo.gateway.database.AddressGateway;
import address.geolocation.com.geo.gateway.exception.AddAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AddAddressUseCase {
    private final AddressGateway addressGateway;
    private final GetGeolocationDataUseCase getGeolocationDataUseCase;

    @Autowired
    public AddAddressUseCase(AddressGateway addressGateway, GetGeolocationDataUseCase getGeolocationDataUseCase) {
        this.addressGateway = addressGateway;
        this.getGeolocationDataUseCase = getGeolocationDataUseCase;
    }

    public AddressDomain execute(AddressDomain addressDomain) throws AddAddressException {
        try {
            if (StringUtils.isEmpty(addressDomain.getLatitude())) {
                GeoLocationDomain geodata = getGeolocationDataUseCase.execute(addressDomain);
                addressDomain.setLatitude(geodata.getLatitude());
                addressDomain.setLongitude(geodata.longitude);
            }

            return addressGateway.add(addressDomain);

        } catch (Exception ex) {
            throw new AddAddressException("Problemas ao salvar endereço");
        }

    }

}
