public class Product {
    // Fields
    String name;
    double price;
    final int productId; // Using for a constant field

    // Constructor
    public Product(String name, double price, int productId) {
        this.name = name;
        this.price = price;
        this.productId = productId;
    }

    // Method
    public void displayInfo() {
        System.out.println("Product ID: " + productId + ", Product: " + name + ", Price: $" + price);
    }
}