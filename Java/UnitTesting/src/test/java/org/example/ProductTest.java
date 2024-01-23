package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    // Test case for checking the correct calculation of discounted price
    @Test
    void applyDiscount() {
        // Arrange: Create a product with an initial price
        Product product = new Product("Laptop", 1000);
        // Act: Apply a discount
        product.applyDiscount(10);
        // Assert: Check if the discounted price is calculated correctly
        assertEquals(900, product.getPrice(), 0.01);
    }

    // Parameterized test for applying discounts with different percentages
    @ParameterizedTest
    @CsvSource({"10, 900", "20, 800", "0, 1000", "50, 500"})
    void applyDiscountWithDifferentPercentages(double discountPercentage, double expectedPrice) {
        // Arrange: Create a product with an initial price
        Product product = new Product("Test product", 1000);
        // Act: Apply a discount with the specified percentage
        product.applyDiscount(discountPercentage);
        // Assert: Check if the discounted price is calculated correctly
        assertEquals(expectedPrice, product.getPrice(), 0.01);
    }

    // Test case for checking the correct handling of invalid discount percentage
    @Test
    void applyInvalidDiscount() {
        // Arrange: Create a product with an initial price
        Product product = new Product("Smartphone", 500);
        // Act & assert: Applying an invalid discount should throw an exception
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-5));
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(110));
    }

    // Test case for checking the correct modification of product name
    @Test
    void modifyProductName() {
        // Arrange: Create a product with an initial name
        Product product = new Product("Tablet", 300);
        // Act: Modify the product name
        product.setName("New Tablet");
        // Assert: Check if the name is modified correctly
        assertEquals("New Tablet", product.getName());
    }

    // Parameterized test for modifying product names
    @ParameterizedTest
    @CsvSource({"Desktop, Desktop pro", "Printer, 3D Printer"})
    void modifyProductNames(String initialName, String modifiedName) {
        // Arrange: Create a product with an initial name
        Product product = new Product(initialName, 400);
        // Act: Modify the product name
        product.setName(modifiedName);
        // Assert: Check if the name is modified correctly
        assertEquals(modifiedName, product.getName());
    }
}