package com.example.flightticketmanagement.repositories;

import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.models.Ticket;
import com.example.flightticketmanagement.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    // Finds tickets for a specific customer
    List<Ticket> findByCustomer(Customer customer);
    // Finds tickets for a specific flight
    List<Ticket> findByFlight(Flight flight);
    // Finds a ticket by its ticket id
    Optional<Ticket> findByTicketId(String ticketId);
}