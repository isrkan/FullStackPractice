import java.util.HashMap;

public class HashMaps {
    public static void main(String[] args) {

        // HashMap to store product prices
        HashMap<String, Double> productPrices = new HashMap<>();

        // Adding initial product prices to the HashMap
        productPrices.put("Laptop", 999.99);
        productPrices.put("Smartphone", 49.99);
        productPrices.put("Headphones", 129.99);
        productPrices.put("Tablet", 199.99);
        // Displaying all product prices
        System.out.println("Product prices:");
        for (String productName : productPrices.keySet()) {
            System.out.println("- " + productName + ": $" + productPrices.get(productName));
        }

        // Check if a product exists and retrieve its price
        String product = "Laptop";
        if (productPrices.containsKey(product)) {
            double price = productPrices.get(product);
            System.out.println("Price of " + product + ": $" + price);
        }
        // Check if a price exists
        double checkPrice = 59.99;
        System.out.println("Is $" + checkPrice + " present? " + productPrices.containsValue(checkPrice));

        // Get the total number of products
        System.out.println("Total number of products: " + productPrices.size());

        // Update the price of a product
        product = "Smartphone";
        double newPrice = 59.99;
        if (productPrices.containsKey(product)) {
            productPrices.put(product, newPrice);
        }
        // Deleting a product
        product = "Headphones";
        if (productPrices.containsKey(product)) {
            productPrices.remove(product);
        }
        System.out.println("\nUpdated product prices:");
        for (String productName : productPrices.keySet()) {
            System.out.println("- " + productName + ": $" + productPrices.get(productName));
        }

        // Compute the total value of all products
        double totalValue = productPrices.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("Total value of all products: $" + totalValue);
    }
}