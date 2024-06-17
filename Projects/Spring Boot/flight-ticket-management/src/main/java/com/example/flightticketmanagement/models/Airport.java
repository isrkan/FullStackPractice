package com.example.flightticketmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters and setters methods
@Entity // Specifies that this class is a JPA entity and will be mapped to a database table
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields as parameters
@NoArgsConstructor // Lombok annotation to generate a no-arguments constructor
public class Airport {
    @Id // Specifies the primary key of the entity
    private String airportCode;
    private String airportName;
    private String city;
    private String country;
    private double latitude;
    private double longitude;
    private String timeZone;
}