package singleton;

// Singleton class to manage device prices
public class PriceManager {
    // Private static instance variable to hold the single instance
    private static PriceManager instance;

    // Private constructor to prevent instantiation from outside
    private PriceManager(String managerName, int employeeNumber) {
    }

    // Public static method to get the single instance
    public static PriceManager getInstance(String managerName, int employeeNumber) {
        // Lazy initialization: Create the instance if it's null
        if (instance == null) {
            instance = new PriceManager(managerName, employeeNumber);
        }
        return instance;
    }

    // Method to update the price of a product
    public void updateDevicePrice(Device device, double newPrice) {
        device.setPrice(newPrice);
        System.out.println("Price for " + device.getName() + " updated to: $" + newPrice);
    }
}