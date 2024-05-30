package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.models.Airport;
import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.repositories.AirlineRepository;
import com.example.flightticketmanagement.repositories.AirportRepository;
import com.example.flightticketmanagement.repositories.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.core.annotation.Order;

@Slf4j
@Component
@Order(3)
public class FlightDataInitializer {

    private AirportRepository airportRepository;
    private AirlineRepository airlineRepository;
    private FlightRepository flightRepository;

    @Autowired
    public FlightDataInitializer(AirportRepository airportRepository,
                                 AirlineRepository airlineRepository,
                                 FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.flightRepository = flightRepository;
    }

    @Bean
    @DependsOn({"preloadAirlineData"})
    public ApplicationRunner preloadFlightData() {
        return args -> {
            // Retrieve the related airports
            Airport bosAirport = airportRepository.findByAirportCode("BOS").orElseThrow(() -> new RuntimeException("Airport not found: JFK"));
            Airport jfkAirport = airportRepository.findByAirportCode("JFK").orElseThrow(() -> new RuntimeException("Airport not found: JFK"));
            Airport lhrAirport = airportRepository.findByAirportCode("LHR").orElseThrow(() -> new RuntimeException("Airport not found: LHR"));
            // Retrieve the related airlines
            Airline aaAirline = airlineRepository.findByIataCode("AA").orElseThrow(() -> new RuntimeException("Airline not found: AA"));

            // Step 1: Create the Flight objects
            Flight aa1011507202408 = new Flight(null, "AA101", aaAirline, jfkAirport, lhrAirport, LocalDate.of(2024, 7, 15), LocalTime.of(8, 0), LocalTime.of(20, 0), 150);
            Flight aa2011007202410 = new Flight(null, "AA201", aaAirline, bosAirport, lhrAirport, LocalDate.of(2024, 7, 10), LocalTime.of(10, 0), LocalTime.of(22, 0), 120);
            // Step 2: Save the Flight objects
            flightRepository.save(aa1011507202408);
            flightRepository.save(aa2011007202410);

            log.info("Flight data preloaded.");
        };
    }
}