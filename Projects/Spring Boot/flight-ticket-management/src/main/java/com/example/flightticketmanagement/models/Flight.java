package com.example.flightticketmanagement.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("airline_iata_code")
    private String airlineIataCode;

    @JsonProperty("origin_airport")
    private String originAirport;

    @JsonProperty("destination_airport")
    private String destinationAirport;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("departure_time_local")
    private LocalTime departureTimeLocal;

    @JsonProperty("landing_time_local")
    private LocalTime landingTimeLocal;

    @JsonProperty("remaining_tickets")
    private int remainingTickets;
}