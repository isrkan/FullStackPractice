package dependencyInjection;

public class ShoppingCart {
    private Product product;

    // Constructor injection: Injecting dependency through the constructor
    // In this case, ShoppingCart depends on a Product, and we inject it during object creation
    public ShoppingCart(Product product) {
        this.product = product;
    }

    // Method to calculate the total price with a discount
    // Method injection: Injecting a dependency through a method
    // In this case, we inject a discount value into the method to customize the behavior
    public double calculateTotalPrice(double discount) {
        // Method injection is useful when we want to apply different discounts for the same cart
        return product.getPrice() - (product.getPrice() * discount / 100);
    }

    // Property to store the current product
    // Property injection: Injecting a dependency through a property or setter
    // In this case, we provide a setter method to dynamically change the product
    public void setProduct(Product product) {
        // Property injection is useful when we want to change the product associated with the cart
        this.product = product;
    }

    // Display information about the current product
    public void displayProductInfo() {
        System.out.println("Current Product: " + product.getName() + ", Price: $" + product.getPrice());
    }
}
