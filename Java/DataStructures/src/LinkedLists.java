import java.util.LinkedList;
import java.util.Collections;

public class LinkedLists {
    public static void main(String[] args) {

        // LinkedList to store product prices
        LinkedList<Double> prices = new LinkedList<>();

        // Adding initial prices to the LinkedList
        prices.add(999.99);
        prices.add(49.99);
        prices.add(129.99);
        // Displaying all prices
        System.out.println("\nCurrent product prices:");
        for (double price : prices) {
            System.out.println("- $" + price);
        }

        // Add a new price at a specific position
        prices.add(0, 79.99);
        // Deleting a price
        double priceToDelete = 49.99;
        if (prices.contains(priceToDelete)) {
            prices.remove(priceToDelete);
        }
        System.out.println("\nCurrent product prices:");
        for (double price : prices) {
            System.out.println("- $" + price);
        }

        // Calculate the total, average, maximum and minimum price
        double total = 0;
        for (double price : prices) {
            total += price;
        }
        double average = total / prices.size();
        System.out.println("Total price: $" + total + ", average price: $" + average);
        System.out.println("Maximum price: $" + Collections.max(prices) + ", minimum price: $" + Collections.min(prices));

        // Update a product price
        if (prices.contains(999.99)) {
            int index = prices.indexOf(999.99);
            prices.set(index, 899.99);
        }
        // Sort prices in ascending order
        prices.sort(Double::compareTo);
        System.out.println("\nSorted prices:");
        for (double price : prices) {
            System.out.println("- $" + price);
        }
    }
}