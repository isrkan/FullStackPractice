package org.example;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Display product information
    public void displayProductInfo() {
        System.out.println("Product: " + name + ", Price: $" + price);
    }

    // Method to apply a discount to the product price
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
        }
        double discountedPrice = price - (price * discountPercentage / 100);
        setPrice(discountedPrice);
    }
}