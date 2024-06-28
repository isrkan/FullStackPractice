package com.example.flightticketmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //  Combination of @Configuration, @EnableAutoConfiguration, and @ComponentScan which sets up the Spring Boot application with sensible defaults
public class FlightTicketManagementApplication {

	public static void main(String[] args) {
		// Starts the entire spring framework by creating an appropriate ApplicationContext instance (depending on the classpath)
		SpringApplication.run(FlightTicketManagementApplication.class, args);
	}
}