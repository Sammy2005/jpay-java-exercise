package com.jumia.jpay.controllers;

import com.jumia.jpay.dtos.CustomerDto;
import com.jumia.jpay.enums.Country;
import com.jumia.jpay.services.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Samuel Mbugua
 */
@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Get all customers
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(customerService.findAllCustomers(pageable));
    }

    /**
     * Find customer by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable Long id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    /**
     * Filter Customer details by country or phone number validity
     *
     * @param country
     * @param state
     * @return
     */
    @GetMapping("/filter")
    public ResponseEntity<List<CustomerDto>> filterByCountryOrState(@RequestParam(value = "country", required = false) Country country,
                                                                    @RequestParam(value = "state", required = false) String state) {
        if (country != null) {
            return ResponseEntity.ok(customerService.filter(country));
        } else {
            return ResponseEntity.ok(customerService.filter(state));
        }
    }
}
