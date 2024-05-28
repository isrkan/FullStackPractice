package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Airport;
import com.example.flightticketmanagement.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AirportDataInitializer {
    @Autowired
    private AirportRepository airportRepository;


    @Bean
    public ApplicationRunner preloadAirportData(AirportRepository airportRepository) {
        return args -> {
            // Step 1: Create the Airport objects
            Airport jfkAirport = new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA", 40.6413, -73.7781, "UTC-5");
            Airport lhrAirport = new Airport("LHR", "Heathrow Airport", "London", "United Kingdom", 51.4700, -0.4543, "UTC+0");

            // Step 2: Save the Airport objects
            airportRepository.save(jfkAirport);
            airportRepository.save(lhrAirport);
        };
    }
}
