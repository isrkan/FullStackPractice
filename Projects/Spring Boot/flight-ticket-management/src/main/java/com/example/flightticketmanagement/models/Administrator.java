package com.example.flightticketmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Administrator implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
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
