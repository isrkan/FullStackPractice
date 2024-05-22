package com.example.flightticketmanagement.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
@Table(name = "airline")
public class Airline {
    @Id
    @JsonProperty("iata_code")
    private String iataCode;

    @JsonProperty("airline_name")
    private String airlineName;

    @JsonProperty("airport_base")
    private String airportBase;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}