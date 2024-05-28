package com.example.flightticketmanagement.data;

import com.example.flightticketmanagement.repositories.AirlineRepository;
import com.example.flightticketmanagement.repositories.AirportRepository;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import com.example.flightticketmanagement.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleanupListener {

    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DatabaseCleanupListener(AirlineRepository airlineRepository,
                                   AirportRepository airportRepository,
                                   FlightRepository flightRepository,
                                   CustomerRepository customerRepository) {
        this.airlineRepository = airlineRepository;
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
        this.customerRepository = customerRepository;
    }

    @EventListener
    public void onApplicationShutdown(ContextClosedEvent event) {
        // Clear all tables
        flightRepository.deleteAll();
        airlineRepository.deleteAll();
        airportRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
