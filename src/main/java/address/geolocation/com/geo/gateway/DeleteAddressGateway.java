package address.geolocation.com.geo.gateway;

import address.geolocation.com.geo.gateway.exception.DeleteAddressException;

public interface DeleteAddressGateway {
    void delete(String id) throws DeleteAddressException;
}
