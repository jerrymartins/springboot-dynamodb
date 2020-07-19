package address.geolocation.com.geo.gateway.database.repository;

import address.geolocation.com.geo.gateway.database.entity.AddressEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

@EnableScan
public interface AddressRepository extends CrudRepository<AddressEntity, String> {

    Optional<AddressEntity> findById(String id);

    Set<AddressEntity> findByCity(String city);

    Set<AddressEntity> findByZipCode(String zipcode);

}
