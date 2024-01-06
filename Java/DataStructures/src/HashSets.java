import java.util.HashSet;

public class HashSets {
    public static void main(String[] args) {

        // HashSet to store retail store types
        HashSet<String> storeTypes = new HashSet<>();

        // Adding initial retail store types to the HashSet
        storeTypes.add("Electronics");
        storeTypes.add("Clothing");
        storeTypes.add("Grocery");
        storeTypes.add("Home furnishings");
        storeTypes.add("Sporting goods");
        // Displaying all store types
        System.out.println("\nRetail store types:");
        for (String type : storeTypes) {
            System.out.println("- " + type);
        }

        // Try to add a duplicate retail store type
        storeTypes.add("Clothing");
        // Check if a store type exists and remove it
        String typeToRemove = "Electronics";
        if (storeTypes.contains(typeToRemove)) {
            storeTypes.remove(typeToRemove);
        }
        System.out.println("\nRetail store types after attempting to add a duplicate and removing another store type:");
        for (String type : storeTypes) {
            System.out.println("- " + type);
        }

        // Create a new HashSet and add store types from another HashSet
        HashSet<String> newStoreTypes = new HashSet<>();
        newStoreTypes.addAll(storeTypes);
        newStoreTypes.add("Books");
        System.out.println("\nNew set of store types:");
        for (String type : newStoreTypes) {
            System.out.println("- " + type);
        }
        // Get the total number of store types
        System.out.println("Total number of store types: " + newStoreTypes.size());

        // Intersection of HashSets
        HashSet<String> intersectionResult = new HashSet<>(storeTypes);
        intersectionResult.retainAll(newStoreTypes);
        System.out.println("\nIntersection of HashSets:");
        for (String type : intersectionResult) {
            System.out.println("- " + type);
        }

        // Convert HashSet to array
        String[] storeArray = new String[storeTypes.size()];
        storeTypes.toArray(storeArray);
    }
}