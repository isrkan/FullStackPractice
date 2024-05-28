package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.models.Airport;
import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.repositories.AirlineRepository;
import com.example.flightticketmanagement.repositories.AirportRepository;
import com.example.flightticketmanagement.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class FlightDataInitializer {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Bean
    @DependsOn({"preloadAirportData", "preloadAirlineData"})
    public ApplicationRunner preloadFlightData(AirportRepository airportRepository,
                                               AirlineRepository airlineRepository,
                                               FlightRepository flightRepository) {
        return args -> {
            // Retrieve the related airports
            Airport jfkAirport = airportRepository.findById("JFK").orElseThrow(() -> new RuntimeException("Airport not found: JFK"));
            Airport lhrAirport = airportRepository.findById("LHR").orElseThrow(() -> new RuntimeException("Airport not found: LHR"));
            // Retrieve the related airlines
            Airline aaAirline = airlineRepository.findById("AA").orElseThrow(() -> new RuntimeException("Airline not found: AA"));

            // Step 1: Create the Flight objects
            Flight aa1011507202408 = new Flight(null, "AA101", aaAirline, jfkAirport, lhrAirport, LocalDate.of(2024, 7, 15), LocalTime.of(8, 0), LocalTime.of(20, 0), 150);

            // Step 2: Save the Flight objects
            flightRepository.save(aa1011507202408);
        };
    }
}

