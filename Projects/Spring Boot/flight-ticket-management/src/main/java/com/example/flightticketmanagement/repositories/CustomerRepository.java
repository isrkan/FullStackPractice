package com.example.flightticketmanagement.repositories;

import com.example.flightticketmanagement.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    // Finds a customer by its username
    Customer findByUsername(String username);
    // Finds a customer by its customer id
    Optional<Customer> findByCustomerId(Long customerId);
}