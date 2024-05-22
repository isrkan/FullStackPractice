package com.example.flightticketmanagement.controllers;

import com.example.flightticketmanagement.models.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.flightticketmanagement.repositories.FlightRepository;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping
    public String flights(@RequestParam(value = "sort", required = false) String sort,
                          @RequestParam(value = "dir", required = false) String dir,
                          Model model) {
        // Read flight data from the database
        List<Flight> flights = flightRepository.findAll();

        // Sort flights based on parameters
        if (sort != null && dir != null) {
            Comparator<Flight> comparator = getComparator(sort);
            if (comparator != null) {
                if ("desc".equals(dir)) {
                    flights = flights.stream()
                            .sorted(comparator.reversed())
                            .collect(Collectors.toList());
                } else {
                    flights = flights.stream()
                            .sorted(comparator)
                            .collect(Collectors.toList());
                }
            }
        }

        // Add flight data to the model
        model.addAttribute("flights", flights);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        return "flights";
    }

    private Comparator<Flight> getComparator(String sort) {
        switch (sort) {
            case "flightNumber":
                return Comparator.comparing(Flight::getFlightNumber);
            case "airlineIataCode":
                return Comparator.comparing(Flight::getAirlineIataCode);
            case "originAirport":
                return Comparator.comparing(Flight::getOriginAirport);
            case "destinationAirport":
                return Comparator.comparing(Flight::getDestinationAirport);
            case "date":
                return Comparator.comparing(Flight::getDate);
            case "departureTimeLocal":
                return Comparator.comparing(Flight::getDepartureTimeLocal);
            case "landingTimeLocal":
                return Comparator.comparing(Flight::getLandingTimeLocal);
            case "remainingTickets":
                return Comparator.comparingInt(Flight::getRemainingTickets);
            default:
                return null;
        }
    }
}