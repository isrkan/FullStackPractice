class GroceryProduct extends Product {
    int quantityInStock;

    public GroceryProduct(String name, double price, String category, int quantityInStock) {
        super(name, price, category);
        this.quantityInStock = quantityInStock;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Quantity in Stock: " + quantityInStock);
    }

    public void restock(int quantity) {
        System.out.println("Restocking " + quantity + " units of " + name);
        this.quantityInStock += quantity;
    }
}
