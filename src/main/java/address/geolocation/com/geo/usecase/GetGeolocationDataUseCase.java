package address.geolocation.com.geo.usecase;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.domain.GeoLocationDomain;
import address.geolocation.com.geo.gateway.GetGeolocationDataGateway;
import address.geolocation.com.geo.gateway.exception.GeolocationDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetGeolocationDataUseCase {

    private final GetGeolocationDataGateway getGeolocationDataGateway;

    @Autowired
    public GetGeolocationDataUseCase(GetGeolocationDataGateway getGeolocationDataGateway) {

        this.getGeolocationDataGateway = getGeolocationDataGateway;
    }

    public GeoLocationDomain execute(AddressDomain addressDomain) throws GeolocationDataException {
        try {
            return getGeolocationDataGateway.get(addressDomain);

        } catch (Exception ex) {
            throw new GeolocationDataException("Problemas ao buscar geolocalização");
        }

    }

}
