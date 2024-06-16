package com.example.flightticketmanagement.repositories;

import com.example.flightticketmanagement.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByUsername(String username);

    Optional<Customer> findByCustomerId(Long customerId);
}