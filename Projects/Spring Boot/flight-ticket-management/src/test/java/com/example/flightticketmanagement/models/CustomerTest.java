package com.example.flightticketmanagement.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
    }

    // Test constructors and getters
    @Test
    public void testCustomerConstructorAndGetters() {
        customer = new Customer(1L, "Scarlett", "Johansson", "456 Hollywood Blvd, Los Angeles, CA 90038", "+13105554567", "6011111111111117", "sjohansson456", "3657482910365748");

        assertEquals(1L, customer.getCustomerId());
        assertEquals("Scarlett", customer.getFirstName());
        assertEquals("Johansson", customer.getLastName());
        assertEquals("456 Hollywood Blvd, Los Angeles, CA 90038", customer.getAddress());
        assertEquals("+13105554567", customer.getPhoneNumber());
        assertEquals("6011111111111117", customer.getCreditCardNumber());
        assertEquals("sjohansson456", customer.getUsername());
        assertEquals("3657482910365748", customer.getPassword());
    }

    // Test setters
    @Test
    public void testCustomerSetters() {
        customer.setCustomerId(1L);
        customer.setFirstName("Scarlett");
        customer.setLastName("Johansson");
        customer.setAddress("456 Hollywood Blvd, Los Angeles, CA 90038");
        customer.setPhoneNumber("+13105554567");
        customer.setCreditCardNumber("6011111111111117");
        customer.setUsername("sjohansson456");
        customer.setPassword("3657482910365748");

        assertEquals(1L, customer.getCustomerId());
        assertEquals("Scarlett", customer.getFirstName());
        assertEquals("Johansson", customer.getLastName());
        assertEquals("456 Hollywood Blvd, Los Angeles, CA 90038", customer.getAddress());
        assertEquals("+13105554567", customer.getPhoneNumber());
        assertEquals("6011111111111117", customer.getCreditCardNumber());
        assertEquals("sjohansson456", customer.getUsername());
        assertEquals("3657482910365748", customer.getPassword());
    }
}