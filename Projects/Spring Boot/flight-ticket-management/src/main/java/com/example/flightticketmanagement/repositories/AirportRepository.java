package com.example.flightticketmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flightticketmanagement.models.Airport;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, String> {
}
