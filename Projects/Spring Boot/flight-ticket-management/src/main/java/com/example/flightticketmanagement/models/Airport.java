package com.example.flightticketmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    private String airportCode;

    private String airportName;
    private String city;
    private String country;
    private double latitude;
    private double longitude;
    private String timeZone;
}