package address.geolocation.com.geo.gateway;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.exception.UpdateAddressException;

public interface UpdateAddressGateway {
    void update(AddressDomain addressDomain) throws UpdateAddressException;
}
