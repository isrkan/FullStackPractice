package com.example.flightticketmanagement.controllers;

import com.example.flightticketmanagement.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.flightticketmanagement.repositories.FlightRepository;

import java.time.LocalDate;
import java.util.List;

// This class serves as a controller in a Spring MVC application, handling HTTP requests related to flights. It maps URLs to methods that interact with the flight data
@Controller // Indicates that this class serves as a controller in a Spring MVC application
@RequestMapping("/flights") // Maps HTTP requests to /flights to this controller
public class FlightController {
    @Autowired
    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Method to handle get requests to /flights, displays scheduled flights
    @GetMapping
    public String flights(Model model) { // The Model object is used to pass data from the controller to the view
        List<Flight> flightList = flightRepository.findByFlightStatus(Flight.FlightStatus.SCHEDULED);
        model.addAttribute("flights", flightList);
        return "flights";
    }

    // Method to handle get requests to /flights/search, allows searching for flights based on criteria
    @GetMapping("/search")
    public String searchFlights(@RequestParam(value = "originAirport", required = false) String originAirportCode,
                                @RequestParam(value = "destinationAirport", required = false) String destinationAirportCode,
                                @RequestParam(value = "date", required = false) String date,
                                Model model) {
        if (originAirportCode != null && destinationAirportCode != null && date != null) { // Checks if all search parameters are provided
            LocalDate flightDate = LocalDate.parse(date);
            // Finds flights matching the search criteria
            List<Flight> flights = flightRepository.findFlights(originAirportCode, destinationAirportCode, flightDate);
            model.addAttribute("flights", flights); // Adds the list of flights to the model
        }
        return "search"; // Returns the view name "search" to be rendered
    }
}