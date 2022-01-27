package com.jumia.jpay.repositories;

import com.jumia.jpay.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
