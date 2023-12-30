package products;

public abstract class Product {
    String name;
    double price;
    private double costPrice; // Private field - only accessible within the same class
    protected int productId; // Protected field - accessible within the same class, within subclasses, and within the same package

    public Product(int productId, String name, double costPrice) {
        this.productId = productId;
        this.name = name;
        this.costPrice = costPrice;
    }

    // Abstract method for displaying information
    public abstract void displayInfo();

    // Concrete method
    public void setPrice(double newPrice) {
        this.price = newPrice;
        System.out.println(name + "'s price has been updated to $" + newPrice);
    }

    // Final method - cannot be overridden by subclasses
    public final double calculateTax() {
        return price * 0.17;
    }

    // Private method - only accessible within the same class
    private double calculateTotalCost() {
        return price * 0.05 + costPrice;
    }

    // Protected method - accessible within the same class, its subclasses, and classes in the same package
    protected double calculateProfit() {
        return price - calculateTotalCost() - calculateTax();
    }
}