import customers.Customer;
import products.ElectronicsProduct;
import products.GroceryProduct;
import products.Product;

public class Main {
    public static void main(String[] args) {
        // Creating instances of final class and subclasses
        Customer customer = new Customer("Michael Michaeli");
        GroceryProduct apple = new GroceryProduct(1,"Apple", 1.99, 1, 100);
        ElectronicsProduct laptop = new ElectronicsProduct(2, "Laptop", 899.99, 600, "Dell");

        customer.purchaseProduct(apple);
        customer.purchaseProduct(laptop);

        apple.orderMoreStock(50);

        // Displaying information using abstract class
        Product product1 = apple;
        product1.displayInfo();

        Product product2 = laptop;
        product2.displayInfo();

        // Using concrete method from abstract class
        product1.setPrice(2.49);
        product1.displayInfo();

        product2.setPrice(799.99);
        product2.displayInfo();
    }
}