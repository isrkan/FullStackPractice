import java.time.LocalDateTime;

public class Product {
    // Fields
    String name;
    double price;
    final int productId; // Using for a constant field
    String manufacturer; // Nullable field
    LocalDateTime productionDate;

    // Constructor
    public Product(String name, double price, int productId, String manufacturer, LocalDateTime productionDate) {
        this.name = name;
        this.price = price;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.productionDate = productionDate;
    }

    // Method
    public void displayInfo() {
        System.out.println("Product ID: " + productId + ", Product: " + name + ", Price: $" + price
                + ", Manufacturer: " + (manufacturer != null ? manufacturer : "N/A")
                + ", Production date: " + (productionDate != null ? productionDate : "N/A"));
    }
}