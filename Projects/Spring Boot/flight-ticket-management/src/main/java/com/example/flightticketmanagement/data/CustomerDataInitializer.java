package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Customer;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// Class for preloading customer data into the database when the application starts
@Component
@Order(4)
public class CustomerDataInitializer {

    @Bean
    public ApplicationRunner preloadCustomerData(CustomerRepository customerRepository) {
        return args -> {
            // Check if a customer with the given username already exists, if not, create and save it
            Customer existingCustomer = customerRepository.findByUsername("tomhanks123");
            if (!(existingCustomer != null)) {
                // Step 1: Create the customer object
                Customer customer1 = new Customer(1L, "Tom", "Hanks", "123 Hollywood Blvd, Los Angeles, CA 90038", "+13105551234", "4111111111111111", "tomhanks123", "1928374657483921");
                // Step 2: Save the customer object
                customerRepository.save(customer1);
            }
            Customer existingCustomer2 = customerRepository.findByUsername("ldicaprio567");
            if (existingCustomer2 == null) {
                Customer customer2 = new Customer(2L, "Leonardo", "DiCaprio", "567 Vine St, Los Angeles, CA 90038", "+13105555678", "4012888888881881", "ldicaprio567", "4839204756019283");
                customerRepository.save(customer2);
            }
        };
    }
}