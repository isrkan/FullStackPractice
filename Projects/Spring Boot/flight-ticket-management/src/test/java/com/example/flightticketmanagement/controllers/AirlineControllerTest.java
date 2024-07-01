package com.example.flightticketmanagement.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AirlineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Test case for the /airline-login endpoint to display the login form
    @Test
    public void testShowLoginForm() throws Exception {
        // Performs a GET request to /airline-login and verifies the response
        mockMvc.perform(get("/airline-login"))
                .andExpect(status().isOk()) // Asserts that the HTTP status is OK (200)
                .andExpect(view().name("airline-login")) // Asserts that the view name is "airline-login"
                .andExpect(model().attributeExists("error")); // Asserts that the model contains the "error" attribute
    }
}