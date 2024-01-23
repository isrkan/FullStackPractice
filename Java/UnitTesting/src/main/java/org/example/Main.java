package org.example;

public class Main {
    public static void main(String[] args) {
        // Create instances of Product
        Product laptop = new Product("Laptop", 1000);
        Product smartphone = new Product("Smartphone", 500);

        // Display product information
        System.out.println("Product information:");
        laptop.displayProductInfo();
        smartphone.displayProductInfo();

        // Apply a discount
        laptop.applyDiscount(10);
        smartphone.applyDiscount(15);

        // Display updated product information after applying discounts
        System.out.println("\nProduct information after applying discounts:");
        laptop.displayProductInfo();
        smartphone.displayProductInfo();
    }
}