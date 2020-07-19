package address.geolocation.com.geo.gateway.database;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.gateway.*;
import address.geolocation.com.geo.gateway.database.entity.AddressEntity;
import address.geolocation.com.geo.gateway.database.repository.AddressRepository;
import address.geolocation.com.geo.gateway.database.translator.AddressDomainToAddressEntityTranslator;
import address.geolocation.com.geo.gateway.database.translator.AddressEntityToAddressDomainTranslator;
import address.geolocation.com.geo.gateway.exception.AddAddressException;
import address.geolocation.com.geo.gateway.exception.SearchAddressException;
import address.geolocation.com.geo.gateway.exception.UpdateAddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressGateway implements SearchAddressByZipCodeGateway, SearchAddressByCityGateway, SearchAddressByIdGateway, AddAddressGateway, UpdateAddressGateway {

    private final AddressRepository addressRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressGateway.class);

    @Autowired
    public AddressGateway(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public AddressDomain add(AddressDomain addressDomain) throws AddAddressException {
        try {
            LOGGER.info("salvando registro {}", addressDomain);
            AddressEntity address = AddressDomainToAddressEntityTranslator.translate(addressDomain);
            return AddressEntityToAddressDomainTranslator.translate(addressRepository.save(address));
        } catch (Exception ex) {
            LOGGER.error("Erro ao salvar endereço {}", ex);
            throw new AddAddressException("Erro ao salvar endereço");
        }
    }

    public Set<AddressDomain> findByZipCode(String zipCode) throws SearchAddressException {
        try {
            LOGGER.info("buscando endereço por zipcode {}", zipCode);
            Set<AddressEntity> addressEntityList = addressRepository.findByZipCode(zipCode);
            Set<AddressDomain> addressDomainList = addressEntityList.stream().map(AddressEntityToAddressDomainTranslator::translate).collect(Collectors.toSet());
            return addressDomainList;

        } catch (Exception exception) {
            LOGGER.error("Erro ao buscar endereços com zipcode {}", zipCode, exception);
            throw new SearchAddressException("erro ao buscar endereço");
        }
    }

    @Override
    public Set<AddressDomain> findByCity(String city) throws SearchAddressException {
        try {
            LOGGER.info("buscando lista de endereços por cidade {}", city);
            Set<AddressEntity> addressEntityList = addressRepository.findByCity(city);
            Set<AddressDomain> addressDomainList = addressEntityList.stream().map(AddressEntityToAddressDomainTranslator::translate).collect(Collectors.toSet());
            return addressDomainList;
        } catch (Exception exception) {
            LOGGER.error("Erro ao buscar endereço com zipcode {}", city, exception);
            throw new SearchAddressException("erro ao buscar endereço");
        }
    }

    public Optional<AddressDomain> findById(String id) throws SearchAddressException {
        try {
            LOGGER.info("buscando endereço por id {}", id);
            Optional<AddressEntity> addressEntity = addressRepository.findById(id);
            Optional<AddressDomain> addressDomain = Optional.empty();
            if (addressEntity.isPresent())  {
                addressDomain = Optional.of(AddressEntityToAddressDomainTranslator.translate(addressEntity.get()));
            }
            return addressDomain;
        } catch (Exception exception) {
            LOGGER.error("Erro ao buscar endereço com id {}", id, exception);
            throw new SearchAddressException("erro ao buscar endereço");
        }

    }

    @Override
    public void update(AddressDomain addressDomain) throws UpdateAddressException {
        try {
            LOGGER.info("Atualizando registro {}", addressDomain);
            Optional<AddressEntity> addressFound = addressRepository.findById(addressDomain.getId());

            if (!addressFound.isPresent()) {
                throw new UpdateAddressException("Endereço não encontrado");
            }

            addressRepository.save(AddressDomainToAddressEntityTranslator.translate(addressDomain));
        } catch (Exception ex) {
            LOGGER.error("Erro ao atualizar registro {}", addressDomain, ex);
            throw new UpdateAddressException("Endereço não encontrado");

        }

    }
}
