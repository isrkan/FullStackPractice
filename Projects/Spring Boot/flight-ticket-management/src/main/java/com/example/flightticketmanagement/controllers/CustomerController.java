package com.example.flightticketmanagement.controllers;

import com.example.flightticketmanagement.models.Flight;
import com.example.flightticketmanagement.models.Customer;
import com.example.flightticketmanagement.models.Ticket;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import com.example.flightticketmanagement.repositories.FlightRepository;
import com.example.flightticketmanagement.repositories.TicketRepository;
import com.example.flightticketmanagement.models.Ticket.BookingStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.BindingResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Controller
public class CustomerController {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final FlightRepository flightRepository;
    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerController(CustomerRepository customerRepository,
                              FlightRepository flightRepository,
                              TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;

    }

    // Show the registration form
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "registration";
    }

    // Handle the form submission
    @PostMapping("/registration")
    public String registerCustomer(@Valid Customer customer, Errors errors, BindingResult result, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            // If there are validation errors, return to the registration form with error messages
            return "registration";
        }
        // customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        log.info("Registering customer: {}", customer);
        customerRepository.save(customer);
        redirectAttributes.addFlashAttribute("message", "Hello, " + customer.getFirstName() + "! You have been successfully registered.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/account")
    public String showAccountPage(Model model, Principal principal) {
        String username = principal.getName(); // Retrieve the authenticated user's details
        Customer customer = customerRepository.findByUsername(username);
        List<Ticket> tickets = ticketRepository.findByCustomer(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("tickets", tickets);
        return "account";
    }

    // Show edit customer details form
    @GetMapping("/account/edit")
    public String showEditCustomerForm(Model model, Principal principal) {
        Customer customer = customerRepository.findByUsername(principal.getName());
        model.addAttribute("customer", customer);
        return "edit-customer";
    }

    // Handle the form submission for updating customer details
    @PostMapping("/account/update")
    public String updateCustomerDetails(@ModelAttribute("customer") Customer updatedCustomer, Errors errors, Principal principal, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "edit-customer";
        }
        Customer currentCustomer = customerRepository.findByUsername(principal.getName());        // Update only the fields that can be changed
        currentCustomer.setAddress(updatedCustomer.getAddress());
        currentCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        currentCustomer.setCreditCardNumber(updatedCustomer.getCreditCardNumber());

        // Save the updated customer
        customerRepository.save(currentCustomer);
        redirectAttributes.addFlashAttribute("message", "Your details have been updated successfully.");
        return "redirect:/account";
    }

    @GetMapping("/account/tickets/edit/{ticketId}")
    public String showEditTicketForm(@PathVariable String ticketId, Model model) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        model.addAttribute("ticket", ticket);
        model.addAttribute("flights", flightRepository.findAll());
        return "edit-ticket";
    }

    @PostMapping("/account/tickets/edit/{ticketId}")
    public String updateTicket(@PathVariable String ticketId, @ModelAttribute Ticket updatedTicket, RedirectAttributes redirectAttributes) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));

        existingTicket.setClassType(updatedTicket.getClassType());
        existingTicket.setSeatNumber(updatedTicket.getSeatNumber());
        ticketRepository.save(existingTicket);

        redirectAttributes.addFlashAttribute("message", "Ticket updated successfully.");
        return "redirect:/account";
    }

    // Handle delete ticket request
    @PostMapping("/account/tickets/delete/{ticketId}")
    public String deleteTicket(@PathVariable String ticketId, RedirectAttributes redirectAttributes) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);

        if (ticket != null) {
            // Update the booking status to CANCELED
            ticket.setBookingStatus(BookingStatus.CANCELLED);
            // Save the updated ticket
            ticketRepository.save(ticket);
            redirectAttributes.addFlashAttribute("message", "Ticket canceled successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Ticket not found.");
        }
        return "redirect:/account";
    }

    @GetMapping("/purchase/{id}")
    public String showPurchasePage(@RequestParam Long id, Model model, Authentication authentication) {
        // Retrieve the flight details
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Flight not found"));
        // Retrieve the customer details
        Customer customer = (Customer) authentication.getPrincipal();
        // Add flight and customer details to the model
        model.addAttribute("flight", flight);
        model.addAttribute("customer", customer);
        // Add class types to the model
        model.addAttribute("classTypes", Ticket.ClassType.values());
        return "purchase";
    }
}