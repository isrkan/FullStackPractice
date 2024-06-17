package com.example.flightticketmanagement.models;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Arrays;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1) // Defines the sequence generator for the primary key
    private Long customerId;
    @NotBlank(message = "First Name is required") // Validation annotation to ensure the field is not blank
    private String firstName;
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "\\+\\d{11,14}", message = "Phone Number must start with '+' followed by 11 to 14 digits") // Validation annotation to ensure the phone number format is correct
    private String phoneNumber;
    @NotBlank(message = "Credit Card Number is required")
    @Pattern(regexp = "\\d{13,19}", message = "Credit Card Number must be between 13 and 19 digits")
    private String creditCardNumber;
    @NotBlank(message = "Username is required")
    @Column(unique = true) // JPA annotation to ensure the username is unique
    @Size(max = 50) // Validation annotation to set the maximum size of the username
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    private String password;

    // Override methods from UserDetails interface for Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Returns the authorities granted to the customer
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true; //by default not expired
    }
    @Override
    public boolean isAccountNonLocked() {
        return true; //by default not blocked
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true; //by default not expired
    }
    @Override
    public boolean isEnabled() {
        return true; //by default enabled
    }
}