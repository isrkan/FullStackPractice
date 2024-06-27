package com.example.flightticketmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Long id;
    private String flightNumber;
    @ManyToOne // Specifies a many-to-one relationship with the Airline entity
    @JoinColumn(name = "airline_iata_code")
    private Airline airline;
    @ManyToOne
    @JoinColumn(name = "origin_airport_code")
    private Airport originAirport;
    @ManyToOne
    @JoinColumn(name = "destination_airport_code")
    private Airport destinationAirport;
    private LocalDate date;
    private LocalTime departureTimeLocal;
    private LocalTime landingTimeLocal;
    private int remainingTickets;
    @Enumerated(EnumType.STRING) // Specifies that the enum will be persisted as a string
    private FlightStatus flightStatus;

    public enum FlightStatus {
        SCHEDULED, CANCELLED, COMPLETED
    }
}