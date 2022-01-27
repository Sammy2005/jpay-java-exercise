package com.jumia.jpay.helpers;

import com.jumia.jpay.enums.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

@Configuration
@RunWith(MockitoJUnitRunner.class)
class PhoneNumberHelperTest {

    @InjectMocks
    PhoneNumberHelper phoneNumberHelper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isValid() {
        var validPhone = phoneNumberHelper.isValid("(212) 698054317");
        var invalidPhone = phoneNumberHelper.isValid("(258) 8237476180");

        assertTrue(validPhone);
        assertFalse(invalidPhone);
    }

    @Test
    void getCountry() {
        var phoneCountry = phoneNumberHelper.getCountry("(212) 698054317");
        var phoneCountry1 = phoneNumberHelper.getCountry("(258) 8237476180");

        assertEquals(Country.Morocco, phoneCountry);
        assertNull(phoneCountry1);
    }

    @Test
    void getCountryIfValid() {
        var phoneCountry = phoneNumberHelper.getCountryIfValid("(212) 698054317");
        var phoneCountry1 = phoneNumberHelper.getCountryIfValid("(258) 8237476180");

        assertEquals(Country.Morocco, phoneCountry);
        assertNull(phoneCountry1);
    }

    @Test
    void getCountryCode() {
        var phoneCountry = phoneNumberHelper.getCountry("(212) 698054317");
        var phoneCountry1 = phoneNumberHelper.getCountry("(258) 8237476180");

        var countryCode = phoneNumberHelper.getCountryCode(phoneCountry);
        var countryCode1 = phoneNumberHelper.getCountryCode(phoneCountry1);

        assertEquals("+212", countryCode);
        assertNull(countryCode1);
    }
}