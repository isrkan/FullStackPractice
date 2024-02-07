package singleton;

// Singleton class to manage Car-related operations
public class CarManager {
    private static CarManager instance;

    // Private constructor to prevent instantiation outside the class
    private CarManager() {
    }

    // Method to get the singleton instance
    public static CarManager getInstance() {
        if (instance == null) {
            instance = new CarManager();
        }
        return instance;
    }

    public void calculateTotalPriceWithTax(Car car, double taxRate) {
        double totalPrice = car.getPrice() * (1 + taxRate / 100);
        System.out.println("Total price for " + car.getName() + " " + car.getModel() + " with " + taxRate + "% tax: $" + totalPrice);
    }

    public void processCarOrder(Car car) {
        System.out.println("Processing order for " + car.getName() + " " + car.getModel() + " with a base price of $" + car.getPrice());
    }
}