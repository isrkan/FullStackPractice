class GroceryProduct implements Product {
    protected String name;
    private double price;
    private int quantityInStock;

    public GroceryProduct(String name, double price, int quantityInStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void displayInfo() {
        System.out.println("Grocery product: " + name + ", Price: $" + price + ", Quantity in stock: " + quantityInStock);
    }

    // Additional method specific to this class
    public void orderMoreStock(int additionalQuantity) {
        quantityInStock += additionalQuantity;
        System.out.println("Ordered " + additionalQuantity + " more units of " + name + ". New stock: " + quantityInStock);
    }
}
