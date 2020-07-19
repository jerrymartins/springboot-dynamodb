package address.geolocation.com.geo.gateway;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.exception.AddAddressException;

public interface AddAddressGateway {
    AddressDomain add(AddressDomain addressDomain) throws AddAddressException;
}
