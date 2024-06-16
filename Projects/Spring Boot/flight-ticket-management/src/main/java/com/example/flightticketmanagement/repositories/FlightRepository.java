package com.example.flightticketmanagement.repositories;

import com.example.flightticketmanagement.models.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.example.flightticketmanagement.models.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.originAirport.airportCode = :originAirportCode " +
            "AND f.destinationAirport.airportCode = :destinationAirportCode " +
            "AND f.date = :date AND f.flightStatus = 'SCHEDULED'")
    List<Flight> findFlights(@Param("originAirportCode") String originAirportCode,
                             @Param("destinationAirportCode") String destinationAirportCode,
                             @Param("date") LocalDate date);

    Optional<Flight> findByFlightNumberAndDate(String flightNumber, LocalDate date);

    List<Flight> findByAirline(Airline airline);
    List<Flight> findByFlightStatus(Flight.FlightStatus flightStatus);
}
