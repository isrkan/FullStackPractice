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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.BindingResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Slf4j
@Controller
@SessionAttributes(names = {"ticket"})
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
    public String showLoginForm(Model model) {
        model.addAttribute("error", true);
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

    // Handle cancel ticket request
    @PostMapping("/account/tickets/cancel/{ticketId}")
    public String deleteTicket(@PathVariable String ticketId, RedirectAttributes redirectAttributes) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);

        if (ticket != null) {
            // Update the booking status to CANCELED
            ticket.setBookingStatus(BookingStatus.CANCELLED);
            // Increment the remainingTickets for the flight
            Flight flight = ticket.getFlight();
            flight.setRemainingTickets(flight.getRemainingTickets() + 1);
            flightRepository.save(flight);
            // Save the updated ticket
            ticketRepository.save(ticket);
            redirectAttributes.addFlashAttribute("message", "Ticket canceled successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Ticket not found.");
        }
        return "redirect:/account";
    }

    @GetMapping("/purchase/{id}")
    public String showPurchasePage(@PathVariable Long id, Model model, Authentication authentication) {
        System.out.println("Is Authenticated: " + (authentication != null && authentication.isAuthenticated()));
        // Retrieve the flight details
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Flight not found"));
        // Retrieve the customer details
        String username = authentication.getName();
        Customer customer = customerRepository.findByUsername(username);
        // Generate a random ticket ID
        // String ticketId = TicketIdGenerator.generateTicketId();
        // Add flight and customer details to the model
        model.addAttribute("flight", flight);
        model.addAttribute("customer", customer);
        // model.addAttribute("ticketId", ticketId);
        // Add class types to the model
        model.addAttribute("classTypes", Ticket.ClassType.values());
        return "purchase";
    }

    @PostMapping("/purchase")
    public String processPurchaseForm(@RequestParam("flightId") Long flightId, @RequestParam("classType") String classType, @RequestParam("seatNumber") String seatNumber, Model model, Authentication authentication) {
        // Retrieve the flight details from the database
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoSuchElementException("Flight not found"));

        // Retrieve the authenticated customer from the database
        String username = authentication.getName();
        Customer customer = customerRepository.findByUsername(username);

        // Generate a ticket ID
        String ticketId = TicketIdGenerator.generateTicketId();

        // Create a new ticket with the provided details
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);
        ticket.setFlight(flight);
        ticket.setCustomer(customer);
        ticket.setClassType(Ticket.ClassType.valueOf(classType));
        ticket.setSeatNumber(seatNumber);
        ticket.setPrice(500); // Assuming a fixed price for now
        ticket.setBookingStatus(Ticket.BookingStatus.PENDING);

        // Save the ticket to the database (or perform any other necessary actions)
        ticketRepository.save(ticket);
        System.out.println("Ticket saved with ID: " + ticketId);

        // Redirect to the payment page
        return "redirect:/payment?ticketId=" + ticketId;
    }

    @GetMapping("/payment")
    public String showPaymentPage(@RequestParam("ticketId") String ticketId, Model model, Authentication authentication) {
        String username = authentication.getName();
        Customer customer = customerRepository.findByUsername(username);
        System.out.println("Received ticketId: " + ticketId);
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found"));
        model.addAttribute("customer", customer);
        model.addAttribute("ticket", ticket);
        model.addAttribute("ticketId", ticketId);
        return "payment";
    }

    @Transactional
    @PostMapping("/confirm-purchase")
    public String processPayment(@RequestParam("expiryDate") String expiryDate, @RequestParam("cvv") String cvv, @RequestParam("ticketId") String ticketId, Model model) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found"));

        // Perform payment validation and processing (this is a placeholder for actual payment logic)
        boolean paymentSuccessful = validateAndProcessPayment(expiryDate, cvv);
        System.out.println("Received ticketId: " + ticket.getTicketId());
        if (paymentSuccessful) {
            // Update the booking status to BOOKED
            ticket.setBookingStatus(Ticket.BookingStatus.BOOKED);
            // Update the remainingTickets for the flight
            Flight flight = ticket.getFlight();
            flight.setRemainingTickets(flight.getRemainingTickets() - 1);
            flightRepository.save(flight);

            ticketRepository.save(ticket);

            // Add flight and ticket details to the model
            model.addAttribute("flight", ticket.getFlight());
            model.addAttribute("customer", ticket.getCustomer());
            model.addAttribute("ticket", ticket);

            // Redirect to the confirmation page
            return "confirmation";
        } else {
            // Handle payment failure (e.g., show an error message)
            model.addAttribute("error", "Payment failed. Please try again.");
            model.addAttribute("ticket", ticket); // Add ticket to model to display on payment page
            return "payment";
        }
    }


    public class TicketIdGenerator {
        public static String generateTicketId() {
            StringBuilder ticketId = new StringBuilder("TCKT");
            Random random = new Random();
            for (int i = 0; i < 8; i++) {
                if (i % 2 == 0) {
                    ticketId.append(random.nextInt(10)); // Add a random digit
                } else {
                    char randomChar = (char) (random.nextInt(26) + 'A'); // Add a random capital letter
                    ticketId.append(randomChar);
                }
            }
            return ticketId.toString();
        }
    }

    private boolean validateAndProcessPayment(String expiryDate, String cvv) {
        return true; // Assuming payment is always successful for this example
    }
}