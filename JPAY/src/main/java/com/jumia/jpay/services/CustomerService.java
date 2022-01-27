package com.jumia.jpay.services;

import com.jumia.jpay.dtos.CustomerDto;
import com.jumia.jpay.enums.Country;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    /**
     * Find All customers
     *
     * @param pageable
     * @return
     */
    List<CustomerDto> findAllCustomers(Pageable pageable);

    /**
     * Find a customer by id
     *
     * @param id
     * @return
     */
    Optional<CustomerDto> findById(Long id);

    /**
     * Filter customer details by country
     *
     * @param country
     * @return
     */
    List<CustomerDto> filter(Country country);

    /**
     * Filter Customer details by phone number validity
     *
     * @param state
     * @return
     */
    List<CustomerDto> filter(String state);

}
