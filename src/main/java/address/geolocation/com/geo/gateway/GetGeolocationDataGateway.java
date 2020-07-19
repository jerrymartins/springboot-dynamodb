package address.geolocation.com.geo.gateway;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.domain.GeoLocationDomain;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface GetGeolocationDataGateway {
    GeoLocationDomain get(AddressDomain address) throws InterruptedException, ApiException, IOException;
}
