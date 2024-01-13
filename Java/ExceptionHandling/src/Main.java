import exceptions.ExpiredProductException;
import exceptions.InvalidProductNameException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // First way: Using IllegalArgumentException directly
        System.out.print("Enter product price: $");
        double price = scanner.nextDouble();
        checkPrice(price);
        System.out.print("Enter available quantity: ");
        int quantity = scanner.nextInt();
        checkQuantity(quantity);

        // Second way: Using custom exception by extending Exception classes
        System.out.print("Enter product name: ");
        String productName = scanner.next();
        checkProductName(productName);
        System.out.print("Enter manufacturing year: ");
        int manufacturingYear = scanner.nextInt();
        checkManufacturingYear(manufacturingYear);

        System.out.println("Product processed successfully! Please provide additional information:");

        // Handling exceptions using try-catch
        try {
            System.out.print("Enter product weight (in kg): ");
            double productWeight = scanner.nextDouble();
            checkProductWeight(productWeight);

            System.out.print("Enter expiration date (yyyy-MM-dd): ");
            String expirationDate = scanner.next();
            checkExpirationDate(expirationDate);

            System.out.print("Enter product rating (1-5): ");
            int productRating = scanner.nextInt();
            checkProductRating(productRating);

            System.out.print("Enter storage temperature: ");
            int storageTemperature = scanner.nextInt();
            checkStorageTemperature(storageTemperature);

        } catch (IllegalArgumentException | ExpiredProductException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("RuntimeException caught: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    static void checkPrice(double price){
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
    }
    static void checkQuantity(int quantity){
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
    }

    static void checkProductName(String productName) {
        if (productName.isBlank()) {
            throw new InvalidProductNameException("Product name cannot be empty.");
        }
    }

    static void checkManufacturingYear(int manufacturingYear) {
        if (manufacturingYear <= 2022) {
            throw new ExpiredProductException("Product is expired.");
        }
    }

    static void checkProductWeight(double productWeight) {
        if (productWeight <= 0) {
            throw new IllegalArgumentException("Product weight must be greater than zero.");
        }
    }

    static void checkExpirationDate(String expirationDate) {
        // Date should be in the future
        if (expirationDate.compareTo("2024-01-01") <= 0) {
            throw new ExpiredProductException("Product has expired.");
        }
    }

    static void checkProductRating(int productRating) {
        if (productRating < 1 || productRating > 5) {
            throw new IllegalArgumentException("Product rating must be between 1 and 5.");
        }
    }

    static void checkStorageTemperature(int storageTemperature) {
        if (storageTemperature < -20 || storageTemperature > 40) {
            throw new RuntimeException("Invalid storage temperature.");
        }
    }
}
