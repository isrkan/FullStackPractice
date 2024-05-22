package com.example.flightticketmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flightticketmanagement.models.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
