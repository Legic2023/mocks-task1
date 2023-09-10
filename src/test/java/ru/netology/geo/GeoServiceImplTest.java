package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static ru.netology.geo.GeoServiceImpl.*;

import java.util.stream.Stream;


public class GeoServiceImplTest {

    @ParameterizedTest
    @MethodSource("testByIpData")
    public void testByIp_locationDefinition_isEqualValue(String ip, String city, Country country, String street, int builing) {
        var expected = new Location(city, country, street, builing);
        var actual = new GeoServiceImpl().byIp(ip);

        Assertions.assertEquals(expected.getCity(), actual.getCity());
        Assertions.assertEquals(expected.getCountry(), actual.getCountry());
        Assertions.assertEquals(expected.getStreet(), actual.getStreet());
        Assertions.assertEquals(expected.getBuiling(), actual.getBuiling());
    }

    public static Stream<Arguments> testByIpData() {
        return Stream.of(
                arguments(LOCALHOST, null, null, null, 0),
                arguments(MOSCOW_IP, "Moscow", Country.RUSSIA, "Lenina", 15),
                arguments(NEW_YORK_IP, "New York", Country.USA, " 10th Avenue", 32));
    }


}