package address.geolocation.com.geo.gateway;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.exception.SearchAddressException;

import java.util.Optional;

public interface SearchAddressByIdGateway {
    Optional<AddressDomain> findById(String search) throws SearchAddressException;
}
