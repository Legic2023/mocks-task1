package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {

    @ParameterizedTest
    @EnumSource(Country.class)
    public void testLocale_returnedText_check(Country country) {
//expected
       String expected1 = "Добро пожаловать";
       String expected2 = "Welcome";
//actual
        String actual = new LocalizationServiceImpl().locale(country);
//assert
        if (country == Country.RUSSIA) {
            Assertions.assertEquals(expected1, actual);
        } else {
            Assertions.assertEquals(expected2, actual);
        }
    }
}
