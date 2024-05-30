package com.example.flightticketmanagement.controllers;

import com.example.flightticketmanagement.models.Customer;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.BindingResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

@Slf4j
@Controller
public class CustomerController {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

    @GetMapping("/account")
    public String showAccountPage(Model model, Principal principal) {
        String username = principal.getName(); // Retrieve the authenticated user's details
        Customer customer = customerRepository.findByUsername(username);
        model.addAttribute("customer", customer);
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
    public String updateCustomerDetails(@Valid @ModelAttribute("customer") Customer updatedCustomer, Errors errors, Principal principal, RedirectAttributes redirectAttributes) {
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
}