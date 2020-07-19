package address.geolocation.com.geo.gateway;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.exception.SearchAddressException;

import java.util.Set;

public interface SearchAddressByCityGateway {
    Set<AddressDomain> findByCity(String city) throws SearchAddressException;
}
