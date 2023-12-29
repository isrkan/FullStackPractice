public class Main {
    public static void main(String[] args) {
        // Creating instances of the subclasses
        GroceryProduct apple = new GroceryProduct("Apple", 1.99, "Fruits",100);
        ElectronicsProduct laptop = new ElectronicsProduct("Laptop", 899.99, "Computers","Dell", 2);

        // Class casting
        // Upcasting (implicit) - the exact types are hidden, treating them as general products
        Product product1 = apple;
        product1.displayInfo();
        Product product2 = laptop;
        product2.displayInfo();

        // Downcasting (explicit)
        if (product1 instanceof GroceryProduct) {
            GroceryProduct castedApple = (GroceryProduct) product1;
            castedApple.setPrice(2.49, true);
            castedApple.restock(50);
            castedApple.displayInfo();
        }

        if (product2 instanceof ElectronicsProduct) {
            ElectronicsProduct castedLaptop = (ElectronicsProduct) product2;
            castedLaptop.applyDiscount(10);
            castedLaptop.extendWarranty(2);
            castedLaptop.displayInfo();
        }
    }
}