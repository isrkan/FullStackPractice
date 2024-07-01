package com.example.flightticketmanagement.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FlightTest {

    private Flight flight;

    @BeforeEach
    public void setUp() {
        flight = new Flight();
    }

    // Test constructors and getters
    @Test
    public void testFlightConstructorAndGetters() {
        Long id = 1L;
        String flightNumber = "AA101";
        Airline airline = new Airline("AA", "American Airlines", "DFW", "userAmerican", "passAmerican123");
        Airport originAirport = new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA", 40.6413, -73.7781, "UTC-5");
        Airport destinationAirport = new Airport("LHR", "Heathrow Airport", "London", "United Kingdom", 51.4700, -0.4543, "UTC+0");
        LocalDate date = LocalDate.of(2024, 7, 15);
        LocalTime departureTime = LocalTime.of(8, 0);
        LocalTime landingTime = LocalTime.of(20, 0);
        int remainingTickets = 150;
        Flight.FlightStatus flightStatus = Flight.FlightStatus.SCHEDULED;

        flight = new Flight(id, flightNumber, airline, originAirport, destinationAirport, date, departureTime, landingTime, remainingTickets, flightStatus);

        assertEquals(id, flight.getId());
        assertEquals(flightNumber, flight.getFlightNumber());
        assertEquals(airline, flight.getAirline());
        assertEquals(originAirport, flight.getOriginAirport());
        assertEquals(destinationAirport, flight.getDestinationAirport());
        assertEquals(date, flight.getDate());
        assertEquals(departureTime, flight.getDepartureTimeLocal());
        assertEquals(landingTime, flight.getLandingTimeLocal());
        assertEquals(remainingTickets, flight.getRemainingTickets());
        assertEquals(flightStatus, flight.getFlightStatus());
    }

    // Test setters
    @Test
    public void testFlightSetters() {
        flight.setId(1L);
        flight.setFlightNumber("AA101");
        flight.setAirline(new Airline("AA", "American Airlines", "DFW", "userAmerican", "passAmerican123"));
        flight.setOriginAirport(new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA", 40.6413, -73.7781, "UTC-5"));
        flight.setDestinationAirport(new Airport("LHR", "Heathrow Airport", "London", "United Kingdom", 51.4700, -0.4543, "UTC+0"));
        flight.setDate(LocalDate.of(2024, 7, 15));
        flight.setDepartureTimeLocal(LocalTime.of(8, 0));
        flight.setLandingTimeLocal(LocalTime.of(20, 0));
        flight.setRemainingTickets(150);
        flight.setFlightStatus(Flight.FlightStatus.SCHEDULED);

        assertEquals(1L, flight.getId());
        assertEquals("AA101", flight.getFlightNumber());
        assertEquals(new Airline("AA", "American Airlines", "DFW", "userAmerican", "passAmerican123"), flight.getAirline());
        assertEquals(new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA", 40.6413, -73.7781, "UTC-5"), flight.getOriginAirport());
        assertEquals(new Airport("LHR", "Heathrow Airport", "London", "United Kingdom", 51.4700, -0.4543, "UTC+0"), flight.getDestinationAirport());
        assertEquals(LocalDate.of(2024, 7, 15), flight.getDate());
        assertEquals(LocalTime.of(8, 0), flight.getDepartureTimeLocal());
        assertEquals(LocalTime.of(20, 0), flight.getLandingTimeLocal());
        assertEquals(150, flight.getRemainingTickets());
        assertEquals(Flight.FlightStatus.SCHEDULED, flight.getFlightStatus());
    }
}
