package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Airport;
import com.example.flightticketmanagement.repositories.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Slf4j
@Component
@Order(1)
public class AirportDataInitializer {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportDataInitializer(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }
    @Bean
    public ApplicationRunner preloadAirportData() {
        return args -> {
            // Step 1: Create the Airport objects
            Airport bosAirport = new Airport("BOS", "Logan International Airport", "Boston", "USA", 42.3656, -71.0096, "UTC-5");
            Airport jfkAirport = new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA", 40.6413, -73.7781, "UTC-5");
            Airport lhrAirport = new Airport("LHR", "Heathrow Airport", "London", "United Kingdom", 51.4700, -0.4543, "UTC+0");
            // Step 2: Save the Airport objects
            airportRepository.save(bosAirport);
            airportRepository.save(jfkAirport);
            airportRepository.save(lhrAirport);

            log.info("Airport data preloaded.");
        };
    }
}