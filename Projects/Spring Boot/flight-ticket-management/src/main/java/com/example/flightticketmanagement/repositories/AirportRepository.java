package com.example.flightticketmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flightticketmanagement.models.Airport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Marks this interface as a Spring Data repository, it extends JpaRepository to provide CRUD operations
public interface AirportRepository extends JpaRepository<Airport, String> {
    // Query method to find an airport by its code. It returns an Optional containing the found airport, or empty if not found
    Optional<Airport> findByAirportCode(String airportCode);
}