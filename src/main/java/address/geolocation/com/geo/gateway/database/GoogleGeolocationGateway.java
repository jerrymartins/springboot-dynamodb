package address.geolocation.com.geo.gateway.database;

import address.geolocation.com.geo.domain.AddressDomain;
import address.geolocation.com.geo.domain.GeoLocationDomain;
import address.geolocation.com.geo.gateway.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleGeolocationGateway implements GetGeolocationDataGateway {

    @Value("${feign.google.geolocation.key}")
    private String key;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleGeolocationGateway.class);

    @Autowired
    public GoogleGeolocationGateway() {}

    @Override
    public GeoLocationDomain get(AddressDomain address) throws InterruptedException, ApiException, IOException {
        LOGGER.info("buscando geolocalização");
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(key)
                .build();
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                address.getStreetName()+","+ address.getNumber()+ address.getZipCode()+ ","+ address.getNeighbourhood()+","+address.getCity()+","+address.getState()+","+address.getCountry()).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return GeoLocationDomain.builder()
                .latitude(gson.toJson(results[0].geometry.location.lat))
                .longitude(gson.toJson(results[0].geometry.location.lng))
                .build();
    }
}
