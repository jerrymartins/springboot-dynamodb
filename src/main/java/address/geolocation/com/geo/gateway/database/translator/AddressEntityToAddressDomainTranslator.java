package address.geolocation.com.geo.gateway.database.translator;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.database.entity.AddressEntity;
import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

@UtilityClass
public class AddressEntityToAddressDomainTranslator {

    public AddressDomain translate(AddressEntity addressEntity) {
        Assert.notNull(addressEntity, "endereço não pode ser nulo");
        return AddressDomain.builder()
                .id(addressEntity.getId())
                .country(addressEntity.getCountry())
                .state(addressEntity.getState())
                .city(addressEntity.getCity())
                .zipCode(addressEntity.getZipCode())
                .neighbourhood(addressEntity.getNeighbourhood())
                .streetName(addressEntity.getStreetName())
                .number(addressEntity.getNumber())
                .complement(addressEntity.getComplement())
                .latitude(addressEntity.getLatitude())
                .longitude(addressEntity.getLongitude())
                .build();
    }
}
