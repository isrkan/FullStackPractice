package com.example.flightticketmanagement.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdministratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Test for displaying the admin login form
    @Test
    public void testShowLoginForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin-login"))
                .andExpect(status().isOk()) // Expect HTTP 200 OK status
                .andExpect(view().name("admin-login")) // Expect the view name to be "admin-login"
                .andExpect(model().attributeExists("error")); // Expect the model to have an attribute "error"
    }
}
