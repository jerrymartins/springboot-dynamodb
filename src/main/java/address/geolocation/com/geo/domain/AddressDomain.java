package address.geolocation.com.geo.domain;

import lombok.*;

@Getter
@Setter
@Builder
@With
public class AddressDomain {
    public String id;
    public String neighbourhood;
    public String streetName;
    public String number;
    public String complement;
    public String city;
    public String state;
    public String country;
    public String zipCode;
    public String latitude;
    public String longitude;
}
