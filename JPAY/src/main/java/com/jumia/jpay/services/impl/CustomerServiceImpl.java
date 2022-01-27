package com.jumia.jpay.services.impl;

import com.jumia.jpay.dtos.CustomerDto;
import com.jumia.jpay.entities.Customer;
import com.jumia.jpay.enums.Country;
import com.jumia.jpay.helpers.PhoneNumberHelper;
import com.jumia.jpay.repositories.CustomerRepository;
import com.jumia.jpay.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Samuel Mbugua
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PhoneNumberHelper phoneNumberHelper;

    public CustomerServiceImpl(CustomerRepository customerRepository, PhoneNumberHelper phoneNumberHelper) {
        this.customerRepository = customerRepository;
        this.phoneNumberHelper = phoneNumberHelper;
    }

    /**
     * Find All customers
     *
     * @param pageable
     * @return
     */
    @Override
    public List<CustomerDto> findAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .stream()
                .map(this::updateCustomerDto)
                .collect(Collectors.toList());
    }

    /**
     * Add country, state and country code to the custer Dto from available customer details
     * @param customer
     * @return
     */
    private CustomerDto updateCustomerDto(Customer customer) {
        CustomerDto customerDto = new ModelMapper().map(customer, CustomerDto.class);
        customerDto.setCountry(phoneNumberHelper.getCountry(customer.getPhone()));
        customerDto.setState(phoneNumberHelper.isValid(customerDto.getPhone()) ? "Valid" : "Invalid");
        if (customerDto.getCountry() != null) {
            customerDto.setCountryCode(phoneNumberHelper.getCountryCode(customerDto.getCountry()));
        }
        return customerDto;
    }

    /**
     * Find a customer by id
     *
     * @param id
     * @return
     */
    @Override
    public Optional<CustomerDto> findById(Long id) {
        return customerRepository.findById(id)
                .map(this::updateCustomerDto);
    }

    /**
     * Filter customer details by country
     *
     * @param country
     * @return
     */
    @Override
    public List<CustomerDto> filter(Country country) {
        return customerRepository.findAll()
                .stream()
                .filter(customer -> phoneNumberHelper.getCountryIfValid(customer.getPhone()) == country)
                .map(this::updateCustomerDto)
                .collect(Collectors.toList());
    }

    /**
     * Filter Customer details by phone number validity
     *
     * @param state
     * @return
     */
    @Override
    public List<CustomerDto> filter(String state) {
        return customerRepository.findAll()
                .stream()
                .map(this::updateCustomerDto)
                .filter(customerDto -> Objects.equals(customerDto.getState(), state))
                .collect(Collectors.toList());
    }
}
