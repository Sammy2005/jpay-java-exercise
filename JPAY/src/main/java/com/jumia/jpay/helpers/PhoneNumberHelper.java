package com.jumia.jpay.helpers;

import com.jumia.jpay.enums.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PhoneNumberHelper {

    HashMap<String, String> countryNumbers = getCountryNumbers();
    HashMap<String, String> countryCodes = getCountryCodes();

    /**
     * Instantiate Countries with their phone number validation RegEx
     * Essentially this can be stored in a database to enable more scalability and ease of change
     *
     * @return
     */
    static HashMap<String, String> getCountryNumbers() {
        HashMap<String, String> phoneRegEx = new HashMap<>();
        phoneRegEx.put("Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$");
        phoneRegEx.put("Ethiopia", "\\(251\\)\\ ?[1-59]\\d{8}$");
        phoneRegEx.put("Morocco", "\\(212\\)\\ ?[5-9]\\d{8}$");
        phoneRegEx.put("Mozambique", "\\(258\\)\\ ?[28]\\d{7,8}$");
        phoneRegEx.put("Uganda", "\\(256\\)\\ ?\\d{9}$");
        return phoneRegEx;
    }

    /**
     * Instantiate a map of countries with their phone codes
     *
     * @return
     */
    static HashMap<String, String> getCountryCodes() {
        HashMap<String, String> codes = new HashMap<>();
        codes.put("Cameroon", "+237");
        codes.put("Ethiopia", "+251");
        codes.put("Morocco", "+212");
        codes.put("Mozambique", "+258");
        codes.put("Uganda", "+256");
        return codes;
    }

    /**
     * Check if a phone number is valid or not
     *
     * @param phoneNumber
     * @return
     */
    public boolean isValid(String phoneNumber) {
        boolean valid = false;
        for (Map.Entry<String, String> countryNum : countryNumbers.entrySet()) {
            Pattern pm = Pattern.compile(countryNum.getValue());
            Matcher match = pm.matcher(phoneNumber);

            valid = valid || (match.find() && match.group().equals(phoneNumber));
        }

        return valid;
    }

    /**
     * Identify country from phone number
     *
     * @param phoneNumber
     * @return
     */
    public Country getCountry(String phoneNumber) {
        for (Map.Entry<String, String> countryNum : countryNumbers.entrySet()) {
            Pattern pm = Pattern.compile(countryNum.getValue());
            Matcher match = pm.matcher(phoneNumber);

            if (match.find() && match.group().equals(phoneNumber)) {
                return Country.valueOf(countryNum.getKey());
            }
        }
        return null;
    }

    /**
     * Get country for valid phone numbers
     *
     * @param phoneNumber
     * @return
     */
    public Country getCountryIfValid(String phoneNumber) {
        if (isValid(phoneNumber)) {
            return getCountry(phoneNumber);
        }
        return null;
    }

    /**
     * Find country codes for valid countries
     *
     * @param country
     * @return
     */
    public String getCountryCode(Country country) {
        if (country == null) {
            return null;
        }
        return countryCodes.get(country.name());
    }
}
