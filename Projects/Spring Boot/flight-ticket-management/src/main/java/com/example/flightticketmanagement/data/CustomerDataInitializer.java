package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Customer;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class CustomerDataInitializer {

    @Bean
    public ApplicationRunner preloadCustomerData(CustomerRepository customerRepository) {
        return args -> {
            // Step 1: Create the Customer objects
            Customer customer1 = new Customer(null, "Tom", "Hanks", "123 Hollywood Blvd, Los Angeles, CA 90038", "+13105551234", "4111111111111111", "tomhanks123", "1928374657483921");

            // Step 2: Save the Customer objects
            customerRepository.save(customer1);
        };
    }
}
