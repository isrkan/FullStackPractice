package products;

public class ElectronicsProduct extends Product {
    String brand;

    public ElectronicsProduct(int productId, String name, double price, double costPrice, String brand) {
        super(productId, name, costPrice);
        this.price = price; // not initialized in the constructor of the abstract class
        this.brand = brand;
    }

    // Implementation of abstract method
    @Override
    public void displayInfo() {
        System.out.println("Electronics product: " + name + ", Price: $" + price +
                ", Brand: " + brand + ", Tax: $" + calculateTax() +
                ", Profit: $" + calculateProfit());
    }
}