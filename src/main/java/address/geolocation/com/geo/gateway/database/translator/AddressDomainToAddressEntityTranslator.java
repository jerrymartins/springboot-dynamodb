package address.geolocation.com.geo.gateway.database.translator;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.database.entity.AddressEntity;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

@UtilityClass
public class AddressDomainToAddressEntityTranslator {

    public static AddressEntity translate(AddressDomain addressDomain) {
        Assert.notNull(addressDomain, "endereço não pode ser nulo");
        AddressEntity address =  AddressEntity.builder()
                .id(addressDomain.getId())
                .city(addressDomain.getCity())
                .neighbourhood(addressDomain.getNeighbourhood())
                .country(addressDomain.getCountry())
                .state(addressDomain.getState())
                .zipCode(addressDomain.getZipCode())
                .streetName(addressDomain.getStreetName())
                .number(addressDomain.getNumber())
                .complement(addressDomain.getComplement())
                .latitude(addressDomain.getLatitude())
                .longitude(addressDomain.getLongitude())
                .build();


        return address;
    }
}
