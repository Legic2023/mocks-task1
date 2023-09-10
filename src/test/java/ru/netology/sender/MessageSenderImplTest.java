package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;


public class MessageSenderImplTest {
    @Test
    void testSend_returnRussianText_expected() {
        //GeoService
        var geoService = Mockito.mock(GeoService.class);
        String countryIP = MOSCOW_IP;
        var location = new Location("Moscow", Country.RUSSIA, null, 0);
        //поведение для byIp
        Mockito.when(geoService.byIp(countryIP)).thenReturn(location);

        //LocalizationService
        var localizationService = Mockito.mock(LocalizationService.class);
        var country = Country.RUSSIA;
        String russianText = "Добро пожаловать";
        //поведение для locale
        Mockito.when(localizationService.locale(country)).thenReturn(russianText);

        //actual
        var messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, countryIP);

        var actual = messageSenderImpl.send(headers);

        //assert
        var expected = russianText;

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testSend_returnEnglishText_expected() {
        //GeoService
        var geoService = Mockito.mock(GeoService.class);
        String countryIP = NEW_YORK_IP;
        var location = new Location("New York", Country.USA, null, 0);
        //поведение для byIp
        Mockito.when(geoService.byIp(countryIP)).thenReturn(location);

        //LocalizationService
        var localizationService = Mockito.mock(LocalizationService.class);
        var country = Country.USA;
        String englishText = "Welcome";
        //поведение для locale
        Mockito.when(localizationService.locale(country)).thenReturn(englishText);

        //actual
        var messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, countryIP);

        var actual = messageSenderImpl.send(headers);

        //assert
        var expected = englishText;

        Assertions.assertEquals(expected, actual);

    }
}