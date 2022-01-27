package com.jumia.jpay.controllers;

import com.jumia.jpay.dtos.CustomerDto;
import com.jumia.jpay.enums.Country;
import com.jumia.jpay.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@Configuration
@RunWith(SpringRunner.class)
class CustomerControllerTest extends AbstractControllerTest {

    public static String baseUrl = "/customers";

    @MockBean
    CustomerService customerService;

    @MockBean
    Pageable pageable;

    @MockBean
    CustomerController customerController;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllCustomers() throws Exception {
        when(customerService.findAllCustomers(pageable)).thenReturn(getCustomerDtoResult());

        var result = mvc
                .perform(MockMvcRequestBuilders.get(baseUrl)
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

        var status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void findCustomerById() throws Exception {
        var customer = this.getCustomerDtoResult().stream()
                .filter(customerDto -> customerDto.getId() != 1L).findFirst();

        when(customerService.findById(1L)).thenReturn(customer);

        var result = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

        var status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void filterByCountryOrState_Country() throws Exception {
        when(customerService.filter(Country.Morocco)).thenReturn(getCustomerDtoResult().stream()
                .filter(customerDto -> customerDto.getCountry() == Country.Morocco)
                .collect(Collectors.toList()));

        var result = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/filter?country=Morocco")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

        var status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void filterByCountryOrState_State() throws Exception {
        when(customerService.filter("Valid")).thenReturn(getCustomerDtoResult().stream()
                .filter(customerDto -> Objects.equals(customerDto.getState(), "Valid"))
                .collect(Collectors.toList()));

        var result = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/filter?state=Valid")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andReturn();

        var status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    public List<CustomerDto> getCustomerDtoResult() {
        List<CustomerDto> customers = new ArrayList<>();

        var customer1 = new CustomerDto();
        customer1.setId(1L);
        customer1.setName("Herald Cooper");
        customer1.setPhone("(212) 698054317");
        customer1.setCountry(Country.Morocco);
        customer1.setCountryCode("+212");
        customer1.setState("Valid");

        var customer2 = new CustomerDto();
        customer2.setId(2L);
        customer2.setName("Lucy White");
        customer2.setPhone("(258) 8237476180");
        customer2.setCountry(null);
        customer2.setCountryCode(null);
        customer2.setState("Valid");

        customers.add(customer1);
        customers.add(customer2);
        return customers;
    }
}