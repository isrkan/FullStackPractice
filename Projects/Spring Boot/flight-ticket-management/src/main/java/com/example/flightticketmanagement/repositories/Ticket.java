package com.example.flightticketmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    private String ticketId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Flight flight;

    @Enumerated(EnumType.STRING)
    private ClassType classType;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private double price;

    public enum BookingStatus {
        BOOKED, CANCELLED, CONFIRMED,CHECKED_IN, COMPLETED
    }

    public enum ClassType {
        ECONOMY, FIRST, BUSINESS
    }
}