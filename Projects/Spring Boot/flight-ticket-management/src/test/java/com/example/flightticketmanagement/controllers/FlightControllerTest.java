package com.example.flightticketmanagement.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.models.Airport;
import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.repositories.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest  // Indicates that this class is a Spring Boot test that loads the application context
@AutoConfigureMockMvc  // Configures MockMvc to be auto-configured for web tests
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;  // Injects the MockMvc instance for performing HTTP requests in the tests

    @MockBean  // MockBean is used to replace actual beans with mock versions, allowing for isolated testing of specific components.
    private FlightRepository flightRepository;

    // Test case for the /flights endpoint to fetch scheduled flights
    @Test
    public void testGetScheduledFlights() throws Exception {
        // Creates mock Airport objects
        Airport jfkAirport = new Airport("JFK", "John F. Kennedy International Airport", "New York", "USA", 40.6413, -73.7781, "UTC-5");
        Airport lhrAirport = new Airport("LHR", "Heathrow Airport", "London", "United Kingdom", 51.4700, -0.4543, "UTC+0");
        Airport tlvAirport = new Airport("TLV", "Ben Gurion Airport", "Tel Aviv", "Israel", 32.0055, 34.8854, "UTC+2");
        Airport bomAirport = new Airport("BOM", "Chhatrapati Shivaji Maharaj International Airport", "Mumbai", "India", 19.0896, 72.8656, "UTC+5:30");
        // Creates mock Airline objects
        Airline aaAirline = new Airline("AA", "American Airlines", "DFW", "userAmerican", "passAmerican123");
        Airline lyAirline = new Airline("LY", "El Al", "TLV", "userElAl", "passElAl123");
        // Creates a list of mock Flight objects with scheduled status
        List<Flight> scheduledFlights = Arrays.asList(
                new Flight(1L, "AA101", aaAirline, jfkAirport, lhrAirport, LocalDate.of(2024, 7, 10), LocalTime.of(8, 0), LocalTime.of(20, 0), 150, Flight.FlightStatus.SCHEDULED),
                new Flight(2L, "LY073", lyAirline, tlvAirport, bomAirport, LocalDate.of(2024, 7, 11), LocalTime.of(9, 0), LocalTime.of(21, 0), 100, Flight.FlightStatus.SCHEDULED)
        );

        // Configures the mocked flightRepository to return the list of scheduled flights
        when(flightRepository.findByFlightStatus(Flight.FlightStatus.SCHEDULED)).thenReturn(scheduledFlights);
        // Performs a GET request to /flights and verifies the response
        mockMvc.perform(get("/flights"))
                .andExpect(status().isOk())  // Asserts that the HTTP status is OK (200)
                .andExpect(view().name("flights"))  // Asserts that the view name is "flights"
                .andExpect(model().attributeExists("flights"))  // Asserts that the model contains the "flights" attribute
                .andExpect(model().attribute("flights", scheduledFlights));  // Asserts that the "flights" attribute matches the scheduledFlights list
    }

    // Test case for the /flights/search endpoint to ensure it responds correctly without parameters
    @Test
    public void testSearchFlights() throws Exception {
        mockMvc.perform(get("/flights/search"))
                .andExpect(status().isOk());  // Asserts that the HTTP status is OK (200)
    }

    // Test case for the /flights/search endpoint with missing parameters to ensure it responds correctly
    @Test
    public void testSearchFlightsMissingParams() throws Exception {
        // Performs a GET request with only the originAirport parameter and verifies the response
        mockMvc.perform(get("/flights/search")
                        .param("originAirport", "JFK"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"));  // Asserts that the view name is "search"

        mockMvc.perform(get("/flights/search")
                        .param("destinationAirport", "LAX"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"));

        mockMvc.perform(get("/flights/search")
                        .param("date", LocalDate.now().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("search"));
    }
}