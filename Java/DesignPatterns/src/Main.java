import dependencyInjection.Product;
import dependencyInjection.ShoppingCart;
import singleton.Device;
import singleton.PriceManager;
import singleton.Car;
import singleton.CarManager;
import factoryMethod.Store;
import factoryMethod.GroceryStore;
import factoryMethod.ElectronicsStore;
import memento.Reservation;
import memento.ReservationHistory;
import memento.ReservationMemento;

public class Main {
    public static void main(String[] args) {

        //// Dependency injection (DI)
        // Create a product instance
        Product laptop = new Product("Laptop", 1200);
        // Create a shopping cart using constructor injection
        ShoppingCart cart1 = new ShoppingCart(laptop);
        cart1.displayProductInfo();
        double totalPrice1 = cart1.calculateTotalPrice(10);
        System.out.println("Total Price (with 10% discount): $" + totalPrice1);
        // Create another shopping cart
        ShoppingCart cart2 = new ShoppingCart(new Product("Smartphone", 500));
        // Use method injection to set the product for the second cart
        cart2.setProduct(laptop);
        cart2.displayProductInfo();
        double totalPrice2 = cart2.calculateTotalPrice(5);
        System.out.println("Total Price (with 5% discount): $" + totalPrice2);
        System.out.println();


        //// Singleton
        // Example 1: Create a product instance
        Device printer = new Device("Printer", 200);
        // Use the singleton PriceManager to update the product price
        PriceManager priceManager = PriceManager.getInstance("Israel Israeli", 12345);
        priceManager.updateDevicePrice(printer, 300);
        // Try to create another instance of PriceManager (should be the same instance)
        PriceManager anotherPriceManager = PriceManager.getInstance("Michael Michaeli", 12346);
        System.out.println("Are the two PriceManager instances the same? " + (priceManager == anotherPriceManager));

        // Example 2: Create a car instance
        Car luxuryCar = new Car("BMW", 50000, "XYZ");
        // Use the Singleton CarManager to perform car-related operations
        CarManager carManager = CarManager.getInstance();
        // Calculate the total price with tax
        carManager.calculateTotalPriceWithTax(luxuryCar, 8.5);
        // Process the car order
        carManager.processCarOrder(luxuryCar);
        System.out.println();


        // Factory method
        // Open a Grocery Store and perform business operations
        Store groceryStore = new GroceryStore("SuperDeal");
        groceryStore.performBusinessOperation("David");
        // Open an Electronics Store and perform business operations
        Store electronicsStore = new ElectronicsStore("ElectroZone");
        electronicsStore.performBusinessOperation("Ben");
        System.out.println();


        // Memento method
        // Create a new reservation
        Reservation reservation = new Reservation("Nathan", 4, "2024-02-10 19:00");
        System.out.println("Initial reservation: " + reservation.getCustomerName() + " - party size: " + reservation.getPartySize() + " - date & time: " + reservation.getDateTime());
        // Save the initial state
        ReservationHistory history = new ReservationHistory();
        history.saveReservation(reservation.save());
        // Update the reservation
        reservation.updateReservation("Jacob", 6, "2024-02-10 20:00");
        System.out.println("Updated reservation: " + reservation.getCustomerName() + " - party size: " + reservation.getPartySize() + " - date & time: " + reservation.getDateTime());
        history.saveReservation(reservation.save());
        // Update the reservation again
        reservation.updateReservation("Leonard", 8, "2024-02-10 21:00");
        System.out.println("Updated reservation: " + reservation.getCustomerName() + " - party size: " + reservation.getPartySize() + " - date & time: " + reservation.getDateTime());
        // Undo the last update
        ReservationMemento previousState = history.undo();
        if (previousState != null) {
            reservation.restore(previousState);
            System.out.println("Reservation after undo: " + reservation.getCustomerName() + " - party size: " + reservation.getPartySize() + " - date & time: " + reservation.getDateTime());
        } else {
            System.out.println("No previous state available to undo.");
        }
    }
}