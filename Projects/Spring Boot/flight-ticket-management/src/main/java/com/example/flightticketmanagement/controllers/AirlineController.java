package com.example.flightticketmanagement.controllers;

import com.example.flightticketmanagement.models.Airline;
import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.models.Ticket;
import com.example.flightticketmanagement.repositories.AirlineRepository;
import com.example.flightticketmanagement.repositories.AirportRepository;
import com.example.flightticketmanagement.repositories.FlightRepository;
import com.example.flightticketmanagement.repositories.TicketRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

// This class serves as a controller in a Spring MVC application, handling HTTP requests related to airlines. It maps URLs to methods that interact with the airline data
@Slf4j
@Controller
public class AirlineController {
    @Autowired
    private final AirlineRepository airlineRepository;
    @Autowired
    private final FlightRepository flightRepository;
    @Autowired
    private final AirportRepository airportRepository;
    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AirlineController(AirlineRepository airlineRepository,
                             FlightRepository flightRepository,
                             AirportRepository airportRepository,
                             TicketRepository ticketRepository) {
        this.airlineRepository = airlineRepository;
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.ticketRepository = ticketRepository;
    }

    // Method to handle get requests to /airline-login, displays the airline login form
    @GetMapping("/airline-login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", true); // Adds an error attribute to the model
        return "airline-login"; // Returns the airline login form view
    }

    // Method to handle get requests to /airline-flights, shows flights for the logged-in airline
    @GetMapping("/airline-flights")
    public String showAirlineFlights(Model model, Principal principal) { // The Principal object represents the currently authenticated user
        String username = principal.getName(); // Retrieve the authenticated user's details
        Optional<Airline> airlineOptional = airlineRepository.findByUsername(username);
        if (airlineOptional.isPresent()) {
            List<Flight> flights = flightRepository.findByAirline(airlineOptional.get()); // Finds flights for the airline
            model.addAttribute("flights", flights); // Adds the list of flights to the model
            return "airline-flights";
        }
        return "redirect:/airline-login"; // Redirects to the airline login page if the airline is not found
    }

    // Method to handle get requests to /airline-flights/add, displays the add flight form
    @GetMapping("/airline-flights/add")
    public String showAddFlightForm(Model model, Principal principal) {
        String loggedInAirlineUsername = principal.getName();
        Airline loggedInAirline = airlineRepository.findByUsername(loggedInAirlineUsername)
                .orElseThrow(() -> new IllegalArgumentException("Logged-in airline not found")); // Finds the airline by username or throws an exception if not found

        model.addAttribute("loggedInAirline", loggedInAirline); // Adds the logged-in airline to the model
        model.addAttribute("airports", airportRepository.findAll());
        model.addAttribute("flight", new Flight()); // Add an empty Flight object to bind form data
        return "add-flight";
    }

    // Method to handle post requests to /airline-flights/add, to handle form submission and add new flight
    @PostMapping("/airline-flights/add")
    public String addFlight(@ModelAttribute("flight") @Valid Flight flight, // Binds the Flight object from the form data to the flight parameter and validates it. The @ModelAttribute annotation tells Spring to populate the flight parameter with form data
                            BindingResult result, // Holds the result of the validation and binding. It contains errors if there are any validation issues
                            Principal principal,
                            RedirectAttributes redirectAttributes) { // Used to pass attributes to a redirect target. It can be used to add flash attributes, which are stored temporarily until the next request, usually for displaying success or error messages
        if (result.hasErrors()) {
            // If there are validation errors, return to the add flight form
            return "add-flight";
        }
        // Set the airline for the flight
        String loggedInAirlineUsername = principal.getName();
        Airline loggedInAirline = airlineRepository.findByUsername(loggedInAirlineUsername)
                .orElseThrow(() -> new IllegalArgumentException("Logged-in airline not found"));
        flight.setAirline(loggedInAirline); // Sets the logged airline for the flight
        flightRepository.save(flight); // Save the new flight
        // Redirect to the airline flights page with a success message
        redirectAttributes.addFlashAttribute("message", "Flight added successfully.");
        return "redirect:/airline-flights";
    }

    // Method to handle get requests to /airline-flights/edit/{flightId}, displays the edit flight form
    @GetMapping("/airline-flights/edit/{flightId}")
    public String showEditFlightForm(@PathVariable Long flightId, // @PathVariable extracts values from the URL
                                     Model model) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) {
            // Handle flight not found
            return "error"; // You can define an error page for this
        }
        model.addAttribute("flight", flight);
        model.addAttribute("airports", airportRepository.findAll()); // Adds the list of airports to the model
        return "edit-flight"; // Return the edit flight form html page
    }

    // Method to handle post requests to /airline-flights/edit/{flightId}, updates flight details
    @PostMapping("/airline-flights/edit/{flightId}")
    public String updateFlightDetails(@PathVariable Long flightId,
                                      @ModelAttribute Flight updatedFlight,
                                      RedirectAttributes redirectAttributes) {
        Flight existingFlight = flightRepository.findById(flightId).orElse(null);
        if (existingFlight == null) {
            return "error";
        }
        // Update the existing flight with the updated details
        existingFlight.setFlightNumber(updatedFlight.getFlightNumber());
        existingFlight.setOriginAirport(updatedFlight.getOriginAirport());
        existingFlight.setDestinationAirport(updatedFlight.getDestinationAirport());
        existingFlight.setDate(updatedFlight.getDate());
        existingFlight.setDepartureTimeLocal(updatedFlight.getDepartureTimeLocal());
        existingFlight.setLandingTimeLocal(updatedFlight.getLandingTimeLocal());
        existingFlight.setRemainingTickets(updatedFlight.getRemainingTickets());
        existingFlight.setFlightStatus(updatedFlight.getFlightStatus());
        // Save the updated flight details to the database
        flightRepository.save(existingFlight);
        // Redirect to the airline flights page with a success message
        redirectAttributes.addFlashAttribute("message", "Flight details updated successfully.");
        return "redirect:/airline-flights";
    }

    // Method to handle post requests to /airline-flights/cancel/{flightId}, cancels a flight
    @PostMapping("/airline-flights/cancel/{flightId}")
    public String cancelFlight(@PathVariable Long flightId,
                               RedirectAttributes redirectAttributes) {
        // Retrieve the flight from the database
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) {
            // Handle flight not found. If the flight is not found, add an error message to the redirect attributes
            redirectAttributes.addFlashAttribute("error", "Flight not found.");
            return "redirect:/airline-flights";
        }
        // Set the flight status to CANCELED
        flight.setFlightStatus(Flight.FlightStatus.CANCELLED);
        flightRepository.save(flight); // Save the updated flight
        // Retrieve all tickets associated with this flight
        List<Ticket> tickets = ticketRepository.findByFlight(flight);
        for (Ticket ticket : tickets) {
            // Set each ticket's booking status to CANCELED
            ticket.setBookingStatus(Ticket.BookingStatus.CANCELLED);
            // Save the updated ticket
            ticketRepository.save(ticket);
        }
        // Redirect to the airline flights page with a success message
        redirectAttributes.addFlashAttribute("message", "Flight canceled successfully.");
        return "redirect:/airline-flights";
    }
}