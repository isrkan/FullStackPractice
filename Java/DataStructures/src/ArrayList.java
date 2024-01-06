import java.util.Collections;
import java.util.List;
import java.util.Iterator;

public class ArrayList {
    public static void main(String[] args) {

        // ArrayList to store product names
        java.util.ArrayList<String> productNames = new java.util.ArrayList<>();

        // Adding initial products to the ArrayList
        productNames.add("Laptop");
        productNames.add("Smartphone");
        productNames.add("Headphones");
        productNames.add("Tablet");
        productNames.add("Camera");
        productNames.add("Speaker");
        // Displaying all products by iterating through the ArrayList
        System.out.println("\nCurrent products:");
        for (String product : productNames) {
            System.out.println("- " + product);
        }

        // Replace an element at a specific index
        productNames.set(2, "Watch");
        // Deleting a product
        String productName = "Smartphone";
        if (productNames.contains(productName)) {
            productNames.remove(productName);
        }
        System.out.println("\nCurrent products:");
        for (String product : productNames) {
            System.out.println("- " + product);
        }

        // Searching for a product
        productName = "Laptop";
        if (productNames.contains(productName)) {
            System.out.println(productName + " is available.");
        }

        // Calculate the number of products
        System.out.println("Total number of products: " + productNames.size());

        // Check if the ArrayList is empty
        if (productNames.isEmpty()) {
            System.out.println("No products available.");
        }

        // Get an element at a specific index
        System.out.println("The first element is: " + productNames.get(0));
        // Find the index of an element
        System.out.println("Tablet found at index " + productNames.indexOf("Tablet"));

        // Create a sublist from an ArrayList
        java.util.ArrayList<String> sublist = new java.util.ArrayList<>(productNames.subList(0, 2));
        System.out.println("\nSublist of products:");
        for (String product : sublist) {
            System.out.println("- " + product);
        }

        // Clone an ArrayList
        java.util.ArrayList<String> copyProductNames = (java.util.ArrayList<String>) productNames.clone();
        // Sort elements in the ArrayList
        Collections.sort(copyProductNames);
        // Join elements into a single string
        System.out.println("\nSorted products: " + String.join(", ", copyProductNames));

        // Reverse the order of elements and swap 2 elements
        Collections.reverse(copyProductNames);
        Collections.swap(productNames, 0, 1);
        System.out.println("\nReversed products: " + String.join(", ", copyProductNames));

        // Convert ArrayList to an Array
        String[] productsArray = new String[productNames.size()];
        productNames.toArray(productsArray);
        for (String product : productsArray) {
            System.out.println("- " + product);
        }

        // Check if the ArrayList contains all elements in a collection
        java.util.ArrayList<String> checkList = new java.util.ArrayList<>(List.of("Watch", "Tablet"));
        if (productNames.containsAll(checkList)) {
            System.out.println("\nThe ArrayList contains all elements in the checkList.");
        }


        ///// Java iterators /////
        // Replace an element using iterators
        Iterator<String> iterator = productNames.iterator();
        while (iterator.hasNext()) {
            String currentProduct = iterator.next();
            if (currentProduct.equals("Watch")) {
                iterator.remove();
                productNames.add("Smartwatch");
                break;
            }
        }
        // Delete products using iterators
        iterator = productNames.iterator();
        int deletedCount = 0;
        while (iterator.hasNext()) {
            String currentProduct = iterator.next();
            if (currentProduct.equals("Camera") || currentProduct.equals("Speaker")) {
                iterator.remove();
                deletedCount++;
                if (deletedCount == 2) {
                    break; // Break out of the loop once two products are deleted
                }
            }
        }
        // Displaying the modified products with iterator
        System.out.println("\nModified products list:");
        iterator = productNames.iterator();
        while (iterator.hasNext()) {
            String product = iterator.next();
            System.out.println("- " + product);
        }
    }
}