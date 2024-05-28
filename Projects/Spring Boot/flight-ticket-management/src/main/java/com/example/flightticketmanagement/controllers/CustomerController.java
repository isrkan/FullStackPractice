package com.example.flightticketmanagement.controllers;

import com.example.flightticketmanagement.models.Customer;
import com.example.flightticketmanagement.repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomerController {

    @Autowired
    private final CustomerRepository customerRepository;

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
        log.info("Registering customer: {}", customer);
        customerRepository.save(customer);
        redirectAttributes.addFlashAttribute("message", "Hello, " + customer.getFirstName() + "! You have been successfully registered.");
        return "redirect:/registration";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}