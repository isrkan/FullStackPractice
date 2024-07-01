package com.example.flightticketmanagement.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AirlineTest {

    private Airline airline;

    @BeforeEach
    public void setUp() {
        airline = new Airline("AA", "American Airlines", "DFW", "userAmerican", "passAmerican123");
    }

    // Test constructor and getter methods
    @Test
    public void testAirlineConstructorAndGetters() {
        assertEquals("AA", airline.getIataCode());
        assertEquals("American Airlines", airline.getAirlineName());
        assertEquals("DFW", airline.getAirportBase());
        assertEquals("userAmerican", airline.getUsername());
        assertEquals("passAmerican123", airline.getPassword());
    }

    // Test setter methods
    @Test
    public void testAirlineSetters() {
        airline.setIataCode("BA");
        airline.setAirlineName("British Airways");
        airline.setAirportBase("LHR");
        airline.setUsername("userBritish");
        airline.setPassword("passBritish123");

        assertEquals("BA", airline.getIataCode());
        assertEquals("British Airways", airline.getAirlineName());
        assertEquals("LHR", airline.getAirportBase());
        assertEquals("userBritish", airline.getUsername());
        assertEquals("passBritish123", airline.getPassword());
    }
}