public class Main {
    public static void main(String[] args) {

        // Creating an object
        Product myProduct = new Product("Milk", 2.49, 123);
        myProduct.displayInfo();

        // Modifying object attributes
        myProduct.name = "Cheese";
        myProduct.price = 4.99;
        myProduct.displayInfo();

    }
}