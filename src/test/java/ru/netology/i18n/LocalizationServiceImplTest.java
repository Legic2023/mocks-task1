package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {

    @ParameterizedTest
    @CsvSource({
            "Добро пожаловать, RUSSIA",
            "Welcome, USA"
    })
    public void testLocale_returnedText_check(String message, Country country) {
//expected
        String expected = message;
//actual
        String actual = new LocalizationServiceImpl().locale(country);
//assert
        Assertions.assertEquals(expected, actual);
    }
}

