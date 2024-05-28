package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AirlineDataInitializer {

    @Autowired
    private AirlineRepository airlineRepository;

    @Bean
    public ApplicationRunner preloadAirlineData(AirlineRepository airlineRepository) {
        return args -> {
            // Step 1: Create the Airline objects
            Airline aaAirline = new Airline("AA", "American Airlines", "DFW", "userAmerican", "passAmerican123");

            // Step 2: Save the Airline objects
            airlineRepository.save(aaAirline);

        };
    }
}

