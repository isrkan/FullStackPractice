package com.example.flightticketmanagement.security;

import com.example.flightticketmanagement.models.Customer;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/account").authenticated()  // Restrict access to the account page
                        .anyRequest().permitAll())  // Permit all requests
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/account") // Redirect to /account after successful login
                        .permitAll())  // Permit access to the login page
                .logout(logout -> logout
                        .logoutSuccessUrl("/"));  // Redirect to home after logout
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Customer customer = customerRepository.findByUsername(username);
            if (customer != null) {
                return customer;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }
}