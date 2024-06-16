package com.example.flightticketmanagement.controllers;

import com.example.flightticketmanagement.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.flightticketmanagement.repositories.FlightRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    @GetMapping
    public String flights(Model model) {
        List<Flight> flightList = flightRepository.findByFlightStatus(Flight.FlightStatus.SCHEDULED);
        model.addAttribute("flights", flightList);
        return "flights";
    }

    @GetMapping("/search")
    public String searchFlights(@RequestParam(value = "originAirport", required = false) String originAirportCode,
                                @RequestParam(value = "destinationAirport", required = false) String destinationAirportCode,
                                @RequestParam(value = "date", required = false) String date,
                                Model model) {
        if (originAirportCode != null && destinationAirportCode != null && date != null) {
            LocalDate flightDate = LocalDate.parse(date);
            List<Flight> flights = flightRepository.findFlights(originAirportCode, destinationAirportCode, flightDate);
            model.addAttribute("flights", flights);
        }
        return "search";
    }
}