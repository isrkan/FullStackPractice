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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;

    @ManyToOne
    private Airline airline;

    @ManyToOne
    private Airport originAirport;

    @ManyToOne
    private Airport destinationAirport;
    private LocalDate date;
    private LocalTime departureTimeLocal;
    private LocalTime landingTimeLocal;
    private int remainingTickets;
}