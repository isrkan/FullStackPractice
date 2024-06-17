package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Administrator;
import com.example.flightticketmanagement.repositories.AdministratorRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

// Class for preloading adminstrator data into the database when the application starts
@Component
@Order(6)
public class AdministratorDataInitializer {

    @Bean
    public ApplicationRunner preloadAdministratorData(AdministratorRepository administratorRepository) {
        return args -> {
            // Check if an administrator with the given username already exists, if not, create and save it
            Optional<Administrator> existingAdmin = administratorRepository.findByUsername("admin");
            if (existingAdmin == null) {
                // Step 1: Create the administrator object
                Administrator admin = new Administrator(1L, "admin", "admin", "admin", "admin");
                // Step 2: Save the administrator object
                administratorRepository.save(admin);
            }
        };
    }
}