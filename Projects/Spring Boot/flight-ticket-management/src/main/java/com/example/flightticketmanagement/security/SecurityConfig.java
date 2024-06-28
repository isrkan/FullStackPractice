package com.example.flightticketmanagement.security;

import com.example.flightticketmanagement.models.Administrator;
import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.models.Customer;
import com.example.flightticketmanagement.repositories.AdministratorRepository;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import com.example.flightticketmanagement.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class configures Spring Security settings for the application, including authentication, authorization, and password encoding
 */

@Configuration // Indicates that this class provides Spring configuration
public class SecurityConfig {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Bean // Bean for password encoder
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    // Defines a SecurityFilterChain bean that configures HTTP security by defining security rules and filters
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/account", "/payment").hasRole("USER")  // Restrict access to the account and payment pages to user role
                        .requestMatchers("/airline-flights").hasRole("AIRLINE") // Restrict access to the airline flights page to airline role
                        .requestMatchers("/admin-page").hasRole("ADMIN") // Restrict access to the admin page to admin role
                        .anyRequest().permitAll())  // Allow unrestricted access to other URLs
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Custom login page URL
                        .defaultSuccessUrl("/") // Redirect to "/" after successful login
                        .successHandler(successHandler)  // Use custom success handler to redirect users after successful login based on their role
                        .loginProcessingUrl("/login")  // URL to submit the login form
                        .failureUrl("/login?error=true") // Redirect to login in case of login failure
                        .permitAll())  // Permit access to the login page for all users
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll());  // Redirect to home after logout
        return http.build(); // Build and return the configured HttpSecurity object
    }

    @Bean
    // Provides user-specific details based on username during authentication
    public UserDetailsService userDetailsService() {
        return username -> {
            // Lookup customer by username
            Customer customer = customerRepository.findByUsername(username);
            if (customer != null) {
                // Create UserDetails object with ROLE_USER authority
                return new org.springframework.security.core.userdetails.User(
                        customer.getUsername(), customer.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
            }
            // Lookup airline by username
            Airline airline = airlineRepository.findByUsername(username).orElse(null);
            if (airline != null) {
                // Create UserDetails object with ROLE_AIRLINE authority
                return new org.springframework.security.core.userdetails.User(
                        airline.getUsername(), airline.getPassword(), AuthorityUtils.createAuthorityList("ROLE_AIRLINE"));
            }
            // Lookup administrator by username
            Administrator administrator = administratorRepository.findByUsername(username).orElse(null);
            if (administrator != null) {
                // Create UserDetails object with ROLE_ADMIN authority
                return new org.springframework.security.core.userdetails.User(
                        administrator.getUsername(), administrator.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
            }
            // Throw exception if no user found with the given username
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    public static class CustomUserDetails extends org.springframework.security.core.userdetails.User {
        private final Object user;

        public CustomUserDetails(Object user, String role) {
            super(((UserDetails) user).getUsername(), ((UserDetails) user).getPassword(),
                    AuthorityUtils.createAuthorityList(role));
            this.user = user;
        }

        public Object getUser() {
            return user;
        }
    }
}