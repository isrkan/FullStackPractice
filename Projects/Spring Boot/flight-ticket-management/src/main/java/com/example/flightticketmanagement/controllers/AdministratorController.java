package com.example.flightticketmanagement.controllers;

import com.example.flightticketmanagement.models.*;
import com.example.flightticketmanagement.repositories.*;
import jakarta.validation.Valid;
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

@Controller
public class AdministratorController {

    @Autowired
    private final AdministratorRepository administratorRepository;
    @Autowired
    private final FlightRepository flightRepository;
    @Autowired
    private final AirlineRepository airlineRepository;
    @Autowired
    private final AirportRepository airportRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdministratorController(AdministratorRepository administratorRepository,
                                   FlightRepository flightRepository,
                                   AirlineRepository airlineRepository,
                                   AirportRepository airportRepository,
                                   CustomerRepository customerRepository,
                                   TicketRepository ticketRepository){
        this.administratorRepository = administratorRepository;
        this.flightRepository = flightRepository;
        this.airlineRepository = airlineRepository;
        this.airportRepository = airportRepository;
        this.customerRepository = customerRepository;
        this.ticketRepository = ticketRepository;
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
            model.addAttribute("airlines", airlineRepository.findAll());
            model.addAttribute("airports", airportRepository.findAll());
            model.addAttribute("flights", flightRepository.findAll());
            model.addAttribute("customers", customerRepository.findAll());
            model.addAttribute("tickets", ticketRepository.findAll());
            return "admin-page";
        }
        return "redirect:/admin-login";
    }

    ///////// Airlines //////////
    // Controller method to display the edit airline form
    @GetMapping("/admin-page/edit-airline/{iataCode}")
    public String showEditAirlineForm(@PathVariable String iataCode, Model model) {
        Airline airline = airlineRepository.findById(iataCode).orElse(null);
        if (airline == null) {
            // Handle airline not found
            return "error"; // You can define an error page for this
        }
        model.addAttribute("airline", airline);
        return "admin-control/admin-edit-airline"; // Return the edit airline form HTML page
    }

    // POST request to update airline details
    @PostMapping("/admin-page/edit-airline/{iataCode}")
    public String updateAirlineDetails(@PathVariable String iataCode,
                                       @ModelAttribute Airline updatedAirline,
                                       RedirectAttributes redirectAttributes) {
        Airline existingAirline = airlineRepository.findById(iataCode).orElse(null);
        if (existingAirline == null) {
            // Handle airline not found
            return "error"; // You can define an error page for this
        }
        // Update the existing airline with the updated details
        existingAirline.setAirlineName(updatedAirline.getAirlineName());
        existingAirline.setAirportBase(updatedAirline.getAirportBase());
        existingAirline.setUsername(updatedAirline.getUsername());
        existingAirline.setPassword(updatedAirline.getPassword());
        // Save the updated airline details to the database
        airlineRepository.save(existingAirline);
        // Redirect to the admin airlines page with a success message
        redirectAttributes.addFlashAttribute("message", "Airline details updated successfully.");
        return "redirect:/admin-page";
    }

    ///////// Airports //////////
    // Controller method to display the edit airline form
    @GetMapping("/admin-page/edit-airport/{airportCode}")
    public String showEditAirportForm(@PathVariable String airportCode, Model model) {
        Airport airport = airportRepository.findById(airportCode).orElse(null);
        if (airport == null) {
            // Handle airport not found
            return "error"; // You can define an error page for this
        }
        model.addAttribute("airport", airport);
        return "admin-control/admin-edit-airport"; // Return the edit airport form HTML page
    }

    @PostMapping("/admin-page/edit-airport/{airportCode}")
    public String updateAirportDetails(@PathVariable String airportCode, @ModelAttribute Airport updatedAirport, RedirectAttributes redirectAttributes) {
        Airport existingAirport = airportRepository.findById(airportCode).orElse(null);
        if (existingAirport == null) {
            // Handle airport not found
            return "error"; // You can define an error page for this
        }
        // Update the existing airport with the updated details
        existingAirport.setAirportName(updatedAirport.getAirportName());
        existingAirport.setCity(updatedAirport.getCity());
        existingAirport.setCountry(updatedAirport.getCountry());
        existingAirport.setLatitude(updatedAirport.getLatitude());
        existingAirport.setLongitude(updatedAirport.getLongitude());
        existingAirport.setTimeZone(updatedAirport.getTimeZone());
        // Save the updated airport details to the database
        airportRepository.save(existingAirport);
        // Redirect to the admin page with a success message
        redirectAttributes.addFlashAttribute("message", "Airport details updated successfully.");
        return "redirect:/admin-page";
    }

    ///////// Flights //////////
    @GetMapping("/admin-page/add-flight")
    public String showAddFlightForm(Model model, Principal principal) {
        model.addAttribute("airlines", airlineRepository.findAll());
        model.addAttribute("airports", airportRepository.findAll());
        model.addAttribute("flight", new Flight()); // Add an empty Flight object to bind form data

        return "admin-control/admin-add-flight";
    }

    // POST request to handle form submission and add new flight
    @PostMapping("/admin-page/add-flight")
    public String addFlight(@ModelAttribute("flight") @Valid Flight flight,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // If there are validation errors, return to the add flight form
            return "admin-control/admin-add-flight";
        }
        // Save the new flight
        flightRepository.save(flight);
        // Redirect to the airline flights page with a success message
        redirectAttributes.addFlashAttribute("message", "Flight added successfully.");
        return "redirect:/admin-page";
    }

    // Controller method to display the edit flight form
    @GetMapping("/admin-page/edit-flight/{flightId}")
    public String showEditFlightForm(@PathVariable Long flightId, Model model) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) {
            // Handle flight not found
            return "error"; // You can define an error page for this
        }
        model.addAttribute("flight", flight);
        model.addAttribute("airlines", airlineRepository.findAll());
        model.addAttribute("airports", airportRepository.findAll());
        return "admin-control/admin-edit-flight"; // Return the edit flight form HTML page
    }

    @PostMapping("/admin-page/edit-flight/{flightId}")
    public String updateFlightDetails(@PathVariable Long flightId, @ModelAttribute Flight updatedFlight, RedirectAttributes redirectAttributes) {
        Flight existingFlight = flightRepository.findById(flightId).orElse(null);
        if (existingFlight == null) {
            // Handle flight not found
            return "error"; // You can define an error page for this
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
        return "redirect:/admin-page";
    }

    // POST request to handle flight deletion
    @PostMapping("/admin-page/delete-flight/{flightId}")
    public String deleteFlight(@PathVariable Long flightId, RedirectAttributes redirectAttributes) {
        // Delete the flight from the database
        flightRepository.deleteById(flightId);

        // Redirect to the airline flights page with a success message
        redirectAttributes.addFlashAttribute("message", "Flight deleted successfully.");
        return "redirect:/admin-page";
    }

    ///////// Customers //////////
    // Controller method to display the edit customer form
    @GetMapping("/admin-page/edit-customer/{customerId}")
    public String showEditCustomerForm(@PathVariable Long customerId, Model model) {
        Customer customer = customerRepository.findByCustomerId(customerId).orElse(null);
        if (customer == null) {
            // Handle customer not found
            return "error"; // You can define an error page for this
        }
        model.addAttribute("customer", customer);
        return "admin-control/admin-edit-customer"; // Return the edit customer form HTML page
    }

    // Controller method to handle the form submission and update the customer details
    @PostMapping("/admin-page/edit-customer/{customerId}")
    public String updateCustomerDetails(@PathVariable Long customerId, @ModelAttribute Customer updatedCustomer, RedirectAttributes redirectAttributes) {
        Customer existingCustomer = customerRepository.findByCustomerId(customerId).orElse(null);
        if (existingCustomer == null) {
            // Handle customer not found
            return "error"; // You can define an error page for this
        }
        // Update the existing customer with the updated details
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        existingCustomer.setCreditCardNumber(updatedCustomer.getCreditCardNumber());
        existingCustomer.setUsername(updatedCustomer.getUsername());
        // Save the updated customer details to the database
        customerRepository.save(existingCustomer);
        // Redirect to the customer management page with a success message
        redirectAttributes.addFlashAttribute("message", "Customer details updated successfully.");
        return "redirect:/admin-page";
    }


    ///////// Tickets //////////
    @GetMapping("/admin-page/add-ticket")
    public String showAddTicketForm(Model model, Principal principal) {
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("flights", flightRepository.findAll());
        model.addAttribute("ticket", new Ticket()); // Add an empty Ticket object to bind form data

        return "admin-control/admin-add-ticket";
    }

    // POST request to handle form submission and add new ticket
    @PostMapping("/admin-page/add-ticket")
    public String addTicket(@ModelAttribute("ticket") @Valid Ticket ticket,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // If there are validation errors, return to the add ticket form
            return "admin-control/admin-add-ticket";
        }
        // Generate a ticket ID
        String ticketId = CustomerController.TicketIdGenerator.generateTicketId();
        ticket.setTicketId(ticketId);
        // Get the flight associated with the ticket
        Flight flight = ticket.getFlight();
        // Decrement the remaining tickets
        flight.setRemainingTickets(flight.getRemainingTickets() - 1);
        flightRepository.save(flight); // Save the updated flight
        // Save the new ticket
        ticketRepository.save(ticket);
        // Redirect to the admin page with a success message
        redirectAttributes.addFlashAttribute("message", "Ticket added successfully.");
        return "redirect:/admin-page";
    }

    // Controller method to display the edit ticket form
    @GetMapping("/admin-page/edit-ticket/{ticketId}")
    public String showEditTicketForm(@PathVariable String ticketId, Model model) {
        Ticket ticket = ticketRepository.findByTicketId(ticketId).orElse(null);
        if (ticket == null) {
            // Handle flight not found
            return "error"; // You can define an error page for this
        }
        model.addAttribute("ticket", ticket);
        model.addAttribute("flights", flightRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        return "admin-control/admin-edit-ticket"; // Return the edit flight form HTML page
    }

    @PostMapping("/admin-page/edit-ticket/{ticketId}")
    public String updateTicketDetails(@PathVariable String ticketId, @ModelAttribute Ticket updatedTicket, RedirectAttributes redirectAttributes) {
        Ticket existingTicket = ticketRepository.findByTicketId(ticketId).orElse(null);
        if (existingTicket == null) {
            // Handle flight not found
            return "error"; // You can define an error page for this
        }
        // Update the existing flight with the updated details
        existingTicket.setTicketId(updatedTicket.getTicketId());
        existingTicket.setCustomer(updatedTicket.getCustomer());
        existingTicket.setFlight(updatedTicket.getFlight());
        existingTicket.setSeatNumber(updatedTicket.getSeatNumber());
        existingTicket.setPrice(updatedTicket.getPrice());
        // Save the updated flight details to the database
        ticketRepository.save(existingTicket);
        // Redirect to the airline flights page with a success message
        redirectAttributes.addFlashAttribute("message", "Ticket details updated successfully.");
        return "redirect:/admin-page";
    }

    // POST request to handle ticket deletion
    @PostMapping("/admin-page/delete-ticket/{ticketId}")
    public String deleteTicket(@PathVariable String ticketId, RedirectAttributes redirectAttributes) {
        // Delete the flight from the database
        ticketRepository.deleteById(ticketId);

        // Redirect to the airline flights page with a success message
        redirectAttributes.addFlashAttribute("message", "Flight deleted successfully.");
        return "redirect:/admin-page";
    }
}