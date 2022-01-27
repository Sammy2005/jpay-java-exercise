package com.jumia.jpay.services.impl;

import com.jumia.jpay.entities.Customer;
import com.jumia.jpay.enums.Country;
import com.jumia.jpay.helpers.PhoneNumberHelper;
import com.jumia.jpay.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@Configuration
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PhoneNumberHelper phoneNumberHelper;

    @InjectMocks
    CustomerServiceImpl customerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllCustomers_shouldReturnListOfCustomerDto() {
        given(customerRepository.findAll(Pageable.unpaged())).willReturn(generateCustomers());

        given(phoneNumberHelper.getCountry("(212) 698054317")).willReturn(Country.Morocco);
        given(phoneNumberHelper.getCountry("(258) 8237476180")).willReturn(null);
        given(phoneNumberHelper.isValid("(212) 698054317")).willReturn(true);
        given(phoneNumberHelper.isValid("(258) 8237476180")).willReturn(false);
        given(phoneNumberHelper.getCountryCode(Country.Morocco)).willReturn("+212");

        var customers = customerService.findAllCustomers(Pageable.unpaged());

        assertEquals(2, customers.size());
        assertEquals(Country.Morocco, customers.get(0).getCountry());
        assertNull(customers.get(1).getCountry());
        assertEquals("Valid", customers.get(0).getState());
        assertEquals("Invalid", customers.get(1).getState());
        assertEquals("+212", customers.get(0).getCountryCode());

    }

    @Test
    void findById() {
        given(customerRepository.findById(1L)).willReturn(Optional.ofNullable(generateCustomers().getContent().get(0)));

        given(phoneNumberHelper.getCountry("(212) 698054317")).willReturn(Country.Morocco);
        given(phoneNumberHelper.getCountry("(258) 8237476180")).willReturn(null);
        given(phoneNumberHelper.isValid("(212) 698054317")).willReturn(true);
        given(phoneNumberHelper.isValid("(258) 8237476180")).willReturn(false);
        given(phoneNumberHelper.getCountryCode(Country.Morocco)).willReturn("+212");

        var optionalCustomer = customerService.findById(1L);

        assertTrue(optionalCustomer.isPresent());
        assertEquals(Country.Morocco, optionalCustomer.get().getCountry());
        assertEquals("Valid", optionalCustomer.get().getState());
        assertEquals("+212", optionalCustomer.get().getCountryCode());
    }

    @Test
    void filter() {
        given(customerRepository.findAll()).willReturn(generateCustomers().getContent());

        given(phoneNumberHelper.getCountry("(212) 698054317")).willReturn(Country.Morocco);
        given(phoneNumberHelper.getCountry("(258) 8237476180")).willReturn(null);
        given(phoneNumberHelper.getCountryIfValid("(212) 698054317")).willReturn(Country.Morocco);
        given(phoneNumberHelper.getCountryIfValid("(258) 8237476180")).willReturn(null);
        given(phoneNumberHelper.isValid("(212) 698054317")).willReturn(true);
        given(phoneNumberHelper.isValid("(258) 8237476180")).willReturn(false);
        given(phoneNumberHelper.getCountryCode(Country.Morocco)).willReturn("+212");

        var customers = customerService.filter(Country.Morocco);

        assertEquals(1, customers.size());
        assertEquals(Country.Morocco, customers.get(0).getCountry());
        assertEquals("Valid", customers.get(0).getState());
        assertEquals("+212", customers.get(0).getCountryCode());
    }

    @Test
    void testFilter() {
        given(customerRepository.findAll()).willReturn(generateCustomers().getContent());

        given(phoneNumberHelper.getCountry("(212) 698054317")).willReturn(Country.Morocco);
        given(phoneNumberHelper.getCountry("(258) 8237476180")).willReturn(null);
        given(phoneNumberHelper.isValid("(212) 698054317")).willReturn(true);
        given(phoneNumberHelper.isValid("(258) 8237476180")).willReturn(false);
        given(phoneNumberHelper.getCountryCode(Country.Morocco)).willReturn("+212");

        var customers = customerService.filter("Invalid");

        assertEquals(1, customers.size());
        assertNull(customers.get(0).getCountry());
        assertEquals("Invalid", customers.get(0).getState());
        assertNull(customers.get(0).getCountryCode());
    }


    static Page<Customer> generateCustomers() {
        List<Customer> customers = new ArrayList<>();

        var customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Herald Cooper");
        customer1.setPhone("(212) 698054317");

        var customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Lucy White");
        customer2.setPhone("(258) 8237476180");

        customers.add(customer1);
        customers.add(customer2);
        return new PageImpl<>(customers);
    }
}