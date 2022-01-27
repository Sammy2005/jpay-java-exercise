package com.jumia.jpay.dtos;

import com.jumia.jpay.enums.Country;
import lombok.Data;

@Data
public class CustomerDto {

    private Long id;

    /**
     * Customer name
     */
    private String name;

    /**
     * Customer phone number
     */
    private String phone;

    /**
     * Customer country from contact code
     */
    private Country country;

    /**
     * Indicate phone number validity
     */
    private String state;

    /**
     * Phone number country code
     */
    private String countryCode;
}
