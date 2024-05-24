package com.example.flightticketmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flightticketmanagement.models.Flight;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f.flightNumber AS flightNumber, f.originAirport AS originAirport, " +
            "f.destinationAirport AS destinationAirport, f.date AS date, " +
            "f.departureTimeLocal AS departureTimeLocal, f.landingTimeLocal AS landingTimeLocal, " +
            "f.remainingTickets AS remainingTickets, a.airlineName AS airlineName " +
            "FROM Flight f INNER JOIN Airline a ON f.airlineIataCode = a.iataCode")
    List<Object[]> findAllFlights();
}
