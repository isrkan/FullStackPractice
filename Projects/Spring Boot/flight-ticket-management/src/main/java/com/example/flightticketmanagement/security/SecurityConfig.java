package com.example.flightticketmanagement.security;

import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.models.Customer;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import com.example.flightticketmanagement.repositories.AirlineRepository;
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
    @Autowired
    private AirlineRepository airlineRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/account", "/payment", "/airline-flights").authenticated()  // Restrict access to the account and airline flights pages
                        .anyRequest().permitAll())  // Permit all requests
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/account") // Redirect to /account after successful login
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true") // Redirect to login with error
                        .permitAll())  // Permit access to the login page
                //.formLogin(formLogin -> formLogin
                //        .loginPage("/airline-login")
                //        .defaultSuccessUrl("/airline-flights")
                //        .loginProcessingUrl("/airline-login")
                //        .failureUrl("/airline-login?error=true")
                //        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll());  // Redirect to home after logout
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Customer customer = customerRepository.findByUsername(username);
            if (customer != null) {
                return customer;
            }
            Airline airline = airlineRepository.findByUsername(username).orElse(null);
            if (airline != null) {
                return airline;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }
}