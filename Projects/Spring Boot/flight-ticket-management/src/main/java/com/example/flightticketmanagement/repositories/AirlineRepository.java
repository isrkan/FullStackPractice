package com.example.flightticketmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flightticketmanagement.models.Airline;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, String> {
    // Finds an airline by its iata code
    Optional<Airline> findByIataCode(String iataCode);

    // Finds an airline by its username
    Optional<Airline> findByUsername(String username);
}