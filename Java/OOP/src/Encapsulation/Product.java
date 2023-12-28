import java.time.LocalDateTime;

public class Product {
    // Private fields
    private String name;
    private double price;
    private final int productId;
    private String manufacturer;
    private LocalDateTime productionDate;

    // Constructor
    public Product(String name, double price, int productId, String manufacturer, LocalDateTime productionDate) {
        setName(name);
        setPrice(price);
        this.productId = productId;
        this.manufacturer = manufacturer;
        setProductionDate(productionDate);
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public LocalDateTime getProductionDate() {
        return productionDate;
    }

    // Setter methods with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name");
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Invalid price. Price cannot be negative.");
        }
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setProductionDate(LocalDateTime productionDate) {
        if (productionDate != null && productionDate.isBefore(LocalDateTime.now())) {
            this.productionDate = productionDate;
        } else {
            System.out.println("Invalid production date. Production date cannot be in the future.");
        }
    }

    public void displayInfo() {
        System.out.println("Product ID: " + productId + ", Product: " + name + ", Price: $" + price
                + ", Manufacturer: " + (manufacturer != null ? manufacturer : "N/A")
                + ", Production date: " + (productionDate != null ? productionDate : "N/A"));
    }
}
