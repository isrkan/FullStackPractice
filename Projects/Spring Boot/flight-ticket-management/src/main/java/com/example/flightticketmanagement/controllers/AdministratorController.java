package com.example.flightticketmanagement.controllers;

import com.example.flightticketmanagement.models.Administrator;
import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.repositories.AdministratorRepository;
import com.example.flightticketmanagement.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AdministratorController {

    @Autowired
    private final AdministratorRepository administratorRepository;
    @Autowired
    private final FlightRepository flightRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdministratorController(AdministratorRepository administratorRepository,
                                   FlightRepository flightRepository){
        this.administratorRepository = administratorRepository;
        this.flightRepository = flightRepository;

    }

    @GetMapping("/admin-login")
    public String showLoginForm() {
        return "admin-login";
    }

    @GetMapping("/admin-page")
    public String showAirlineFlights(Model model, Principal principal) {
        String username = principal.getName(); // Retrieve the authenticated user's details
        Optional<Administrator> administratorOptional = administratorRepository.findByUsername(username);
        if (administratorOptional.isPresent()) {
            Administrator administrator = administratorOptional.get();
            model.addAttribute("administrator", administrator);
            return "admin-page";
        }
        return "redirect:/admin-login";
    }
}
