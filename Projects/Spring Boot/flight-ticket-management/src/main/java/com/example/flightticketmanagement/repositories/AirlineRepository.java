package com.example.flightticketmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flightticketmanagement.models.Airline;

public interface AirlineRepository extends JpaRepository<Airline, String> {
}
