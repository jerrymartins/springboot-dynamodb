package address.geolocation.com.geo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8042/",
        "amazon.aws.accesskey=",
        "amazon.aws.secretkey=" })
class GeoApplicationTests {
}
