package com.example.flightticketmanagement.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TicketTest {
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        ticket = new Ticket();
    }

    // Test constructors and getters
    @Test
    public void testTicketConstructorAndGetters() {
        Customer customer = new Customer();
        customer.setUsername("testuser");
        Flight flight = new Flight();
        flight.setFlightNumber("AA101");

        ticket = new Ticket("T123", customer, flight, Ticket.ClassType.ECONOMY, "A23", Ticket.BookingStatus.BOOKED, 250.0);

        assertEquals("T123", ticket.getTicketId());
        assertEquals(customer, ticket.getCustomer());
        assertEquals(flight, ticket.getFlight());
        assertEquals(Ticket.ClassType.ECONOMY, ticket.getClassType());
        assertEquals("A23", ticket.getSeatNumber());
        assertEquals(Ticket.BookingStatus.BOOKED, ticket.getBookingStatus());
        assertEquals(250.0, ticket.getPrice(), 0.0001);
    }

    // Test setters
    @Test
    public void testTicketSetters() {
        Customer customer = new Customer();
        customer.setUsername("testuser");
        Flight flight = new Flight();
        flight.setFlightNumber("AA101");

        ticket.setTicketId("T456");
        ticket.setCustomer(customer);
        ticket.setFlight(flight);
        ticket.setClassType(Ticket.ClassType.FIRST);
        ticket.setSeatNumber("B12");
        ticket.setBookingStatus(Ticket.BookingStatus.CONFIRMED);
        ticket.setPrice(500.0);

        assertEquals("T456", ticket.getTicketId());
        assertEquals(customer, ticket.getCustomer());
        assertEquals(flight, ticket.getFlight());
        assertEquals(Ticket.ClassType.FIRST, ticket.getClassType());
        assertEquals("B12", ticket.getSeatNumber());
        assertEquals(Ticket.BookingStatus.CONFIRMED, ticket.getBookingStatus());
        assertEquals(500.0, ticket.getPrice(), 0.0001);
    }
}
