package com.example.flightticketmanagement.config;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import com.example.flightticketmanagement.repositories.FlightRepository;
import com.example.flightticketmanagement.repositories.AirlineRepository;
import com.example.flightticketmanagement.repositories.AirportRepository;
import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.models.Airport;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DataInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {
        try {
            // Read and parse JSON files
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);

            InputStream flightInputStream = getClass().getResourceAsStream("/data/flights.json");
            FlightsWrapper flightsWrapper = objectMapper.readValue(flightInputStream, FlightsWrapper.class);

            InputStream airlineInputStream = getClass().getResourceAsStream("/data/airlines.json");
            AirlinesWrapper airlinesWrapper = objectMapper.readValue(airlineInputStream, AirlinesWrapper.class);

            InputStream airportInputStream = getClass().getResourceAsStream("/data/airports.json");
            AirportsWrapper airportsWrapper = objectMapper.readValue(airportInputStream, AirportsWrapper.class);

            // Create database
            createDatabase();
            populateDatabase(flightsWrapper.getFlights(), airlinesWrapper.getAirlines(), airportsWrapper.getAirports());

            System.out.println("Data initialization completed successfully.");
        } catch (IOException e) {
            System.err.println("Error initializing data: " + e.getMessage());
        }
    }

    // Create database
    private void createDatabase() {
        try {
            jdbcTemplate.execute("CREATE DATABASE flight_ticket_management");
            System.out.println("Database created successfully.");
        } catch (Exception e) {
            System.out.println("Database already exists.");
        }

        // Create table for Flight entity
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS flight ("
                + "id SERIAL PRIMARY KEY,"
                + "flight_number VARCHAR(10),"
                + "airline_iata_code VARCHAR(10),"
                + "origin_airport VARCHAR(10),"
                + "destination_airport VARCHAR(10),"
                + "date DATE,"
                + "departure_time_local TIME,"
                + "landing_time_local TIME,"
                + "remaining_tickets INTEGER"
                + ")");

        // Create table for Airline entity
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS airline ("
                + "id SERIAL PRIMARY KEY,"
                + "iata_code VARCHAR(10),"
                + "airline_name VARCHAR(100),"
                + "airport_base VARCHAR(10),"
                + "username VARCHAR(100),"
                + "password VARCHAR(100)"
                + ")");

        // Create table for Airport entity
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS airport ("
                + "id SERIAL PRIMARY KEY,"
                + "airport_code VARCHAR(10),"
                + "airport_name VARCHAR(100),"
                + "city VARCHAR(100),"
                + "country VARCHAR(100),"
                + "latitude DECIMAL(9,6),"
                + "longitude DECIMAL(9,6),"
                + "time_zone VARCHAR(20)"
                + ")");
    }

    private void populateDatabase(List<Flight> flights, List<Airline> airlines, List<Airport> airports) {
        // Save data to the database using repositories
        flightRepository.saveAll(flights);
        airlineRepository.saveAll(airlines);
        airportRepository.saveAll(airports);
    }

    // Implement a shutdown hook to delete the database
    @EventListener(ContextClosedEvent.class)
    public void deleteDatabase() {
        jdbcTemplate.execute("DROP DATABASE IF EXISTS flight_ticket_management");
        System.out.println("Database deleted.");
    }

    // Wrapper classes for deserialization
    @Data
    static class FlightsWrapper {
        private List<Flight> flights;
    }

    @Data
    static class AirlinesWrapper {
        private List<Airline> airlines;
    }

    @Data
    static class AirportsWrapper {
        private List<Airport> airports;
    }
}