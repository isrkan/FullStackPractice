package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.models.Customer;
import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.models.Ticket;
import com.example.flightticketmanagement.repositories.TicketRepository;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import com.example.flightticketmanagement.repositories.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

// Class for preloading ticket data into the database when the application starts
@Slf4j
@Component
@Order(5)
public class TicketDataInitializer {

    private FlightRepository flightRepository;
    private CustomerRepository customerRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public TicketDataInitializer(CustomerRepository customerRepository,
                                 TicketRepository ticketRepository,
                                 FlightRepository flightRepository) {
        this.customerRepository = customerRepository;
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
    }

    @Bean
    @DependsOn({"preloadAirlineData"})
    public ApplicationRunner preloadTicketData() {
        return args -> {
            // Retrieve the related flights
            Optional<Flight> flightOptional1 = flightRepository.findByFlightNumberAndDate("AA101", LocalDate.of(2024, 7, 15));
            Flight flight1 = flightOptional1.get();
            Optional<Flight> flightOptional2 = flightRepository.findByFlightNumberAndDate("LY073", LocalDate.of(2024, 7, 1));
            Flight flight2 = flightOptional2.get();
            // Retrieve the related customers
            Customer customer1 = customerRepository.findByUsername("tomhanks123");

            // Check if the ticket exists; if not, create and save it
            if (!ticketRepository.existsById("TCKT1A2B3C4D5")) {
                // Step 1: Create the ticket object
                Ticket ticket1 = new Ticket("TCKT1A2B3C4D5", customer1, flight1, Ticket.ClassType.ECONOMY, "12A", Ticket.BookingStatus.CONFIRMED,450.00);
                // Step 2: Save the ticket object
                ticketRepository.save(ticket1);
            }
            if (!ticketRepository.existsById("TCKT6E7F8G9H0")) {
                Ticket ticket1 = new Ticket("TCKT6E7F8G9H0", customer1, flight2, Ticket.ClassType.BUSINESS, "5B", Ticket.BookingStatus.BOOKED,860.00);
                ticketRepository.save(ticket1);
            }
        };
    }
}