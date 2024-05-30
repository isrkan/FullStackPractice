package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.repositories.AirlineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Slf4j
@Component
@Order(2)
public class AirlineDataInitializer {
    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineDataInitializer(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Bean
    @DependsOn({"preloadAirportData"})
    public ApplicationRunner preloadAirlineData() {
        return args -> {
            // Step 1: Create the Airline objects
            Airline aaAirline = new Airline("AA", "American Airlines", "DFW", "userAmerican", "passAmerican123");
            // Step 2: Save the Airline objects
            airlineRepository.save(aaAirline);

            log.info("Airline data preloaded.");
        };
    }
}