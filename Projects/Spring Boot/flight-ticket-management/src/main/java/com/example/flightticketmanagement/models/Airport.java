package com.example.flightticketmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @JsonProperty("airport_code")
    private String airportCode;

    @JsonProperty("airport_name")
    private String airportName;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("time_zone")
    private String timeZone;
}