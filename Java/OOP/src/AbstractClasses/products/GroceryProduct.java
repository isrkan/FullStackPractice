package products;

public class GroceryProduct extends Product {
    int quantityInStock;

    public GroceryProduct(int productId, String name, double price, double costPrice, int quantityInStock) {
        super(productId, name, costPrice);
        this.price = price; // not initialized in the constructor of the abstract class
        this.quantityInStock = quantityInStock;
    }

    // Implementation of abstract method
    @Override
    public void displayInfo() {
        System.out.println("Grocery product: " + name + ", Price: $" + price +
                ", Quantity in stock: " + quantityInStock + ", Tax: $" + calculateTax() +
                ", Profit: $" + calculateProfit());
    }

    // Method using protected field from superclass
    public void orderMoreStock(int additionalQuantity) {
        quantityInStock += additionalQuantity;
        System.out.println("Ordered " + additionalQuantity + " more units of " + name + " Product ID: " + productId + ". New stock: " + quantityInStock);
    }
}