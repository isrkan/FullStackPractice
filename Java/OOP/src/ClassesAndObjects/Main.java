import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // Creating an object
        LocalDateTime productionDate = LocalDateTime.of(2023, 1, 1, 10, 0); // Example production date
        Product myProduct = new Product("Milk", 2.49, 123, "Tnuva Inc.", productionDate);
        myProduct.displayInfo();

        // Modifying object attributes
        myProduct.name = "Cheese";
        myProduct.price = 4.99;
        myProduct.manufacturer = null;
        myProduct.productionDate = LocalDateTime.now();
        myProduct.displayInfo();
    }
}