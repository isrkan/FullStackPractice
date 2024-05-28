package com.example.flightticketmanagement.models;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long customerId;

    @NotBlank(message = "First Name is required")
    private String firstName;
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "\\+\\d{11,14}", message = "Phone Number must start with '+' followed by 11 to 14 digits")
    private String phoneNumber;
    @NotBlank(message = "Credit Card Number is required")
    @Pattern(regexp = "\\d{13,19}", message = "Credit Card Number must be between 13 and 19 digits")
    private String creditCardNumber;
    @NotBlank(message = "Username is required")
    @Column(unique = true)
    @Size(max = 50)
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 20, message = "Password must be between 4 and 12 characters")
    private String password;
}
