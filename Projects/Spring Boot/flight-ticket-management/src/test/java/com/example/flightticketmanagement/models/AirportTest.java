package com.example.flightticketmanagement.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AirportTest {

    private Airport airport;

    @BeforeEach
    public void setUp() {
        airport = new Airport();
    }

    // Test constructors and getters
    @Test
    public void testAirportConstructorAndGetters() {
        airport = new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA", 40.6413, -73.7781, "UTC-5");

        assertEquals("JFK", airport.getAirportCode());
        assertEquals("John F. Kennedy International Airport", airport.getAirportName());
        assertEquals("New York", airport.getCity());
        assertEquals("USA", airport.getCountry());
        assertEquals(40.6413, airport.getLatitude(), 0.0001);
        assertEquals(-73.7781, airport.getLongitude(), 0.0001);
        assertEquals("UTC-5", airport.getTimeZone());
    }

    // Test setters
    @Test
    public void testAirportSetters() {
        airport.setAirportCode("LHR");
        airport.setAirportName("Heathrow Airport");
        airport.setCity("London");
        airport.setCountry("United Kingdom");
        airport.setLatitude(51.4700);
        airport.setLongitude(-0.4543);
        airport.setTimeZone("UTC+0");

        assertEquals("LHR", airport.getAirportCode());
        assertEquals("Heathrow Airport", airport.getAirportName());
        assertEquals("London", airport.getCity());
        assertEquals("United Kingdom", airport.getCountry());
        assertEquals(51.4700, airport.getLatitude());
        assertEquals(-0.4543, airport.getLongitude());
        assertEquals("UTC+0", airport.getTimeZone());
    }
}
