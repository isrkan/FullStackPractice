package customers;

import products.Product;

// Final class - cannot be subclassed
public final class Customer {
    String name;

    public Customer(String name) {
        this.name = name;
    }

    public void purchaseProduct(Product product) {
        System.out.println(name + " is purchasing:");
        product.displayInfo();
        System.out.println();
    }
}