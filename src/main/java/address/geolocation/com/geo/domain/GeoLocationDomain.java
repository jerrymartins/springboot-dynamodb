package address.geolocation.com.geo.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeoLocationDomain {
    public String latitude;
    public String longitude;
}
