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
        // List<Flight> flights = flightRepository.findAll();
        List<Object[]> flights = flightRepository.findAllFlights();

        // Sort flights based on parameters
        if (sort != null && dir != null) {
            Comparator<Object[]> comparator = getComparator(sort);
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

    private Comparator<Object[]> getComparator(String sort) {
        switch (sort) {
            case "flightNumber":
                return Comparator.comparing(objects -> (String) objects[0]); // flightNumber
            case "airlineName":
                return Comparator.comparing(objects -> (String) objects[7]); // airlineName
            case "originAirport":
                return Comparator.comparing(objects -> (String) objects[1]); // originAirport
            case "destinationAirport":
                return Comparator.comparing(objects -> (String) objects[2]); // destinationAirport
            case "date":
                return Comparator.comparing(objects -> (String) objects[3]); // date
            case "departureTimeLocal":
                return Comparator.comparing(objects -> (String) objects[4]); // departureTimeLocal
            case "landingTimeLocal":
                return Comparator.comparing(objects -> (String) objects[5]); // landingTimeLocal
            case "remainingTickets":
                return Comparator.comparing(objects -> (Integer) objects[6]); // remainingTickets
            default:
                return null;
        }
    }
}