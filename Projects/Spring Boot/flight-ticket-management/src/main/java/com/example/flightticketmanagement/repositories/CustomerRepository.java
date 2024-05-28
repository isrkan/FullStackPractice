package com.example.flightticketmanagement.repositories;

import com.example.flightticketmanagement.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
