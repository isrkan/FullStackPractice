import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // Creating an object with valid values
        LocalDateTime productionDate = LocalDateTime.of(2023, 1, 1, 10, 0); // Example production date
        Product productOne = new Product("Milk", 2.49, 123, "Tnuva Inc.", productionDate);
        productOne.displayInfo();

        // Creating an object with an invalid values
        Product productTwo = new Product(null, -1.0, 456, "Straus Inc.", LocalDateTime.now());
        productTwo.displayInfo(); // Displaying info will show default values

        // Modifying object attributes with valid values
        productTwo.setName("Cheese");
        productTwo.setPrice(4.99);
        productTwo.setManufacturer(null);
        productTwo.setProductionDate(LocalDateTime.of(2023, 1, 1, 23, 0));
        productTwo.displayInfo();

        // Modifying object attributes with invalid values - will keep the existing values
        productOne.setName(null);
        productOne.setPrice(-1.0);
        productOne.setProductionDate(LocalDateTime.of(2024, 1, 1, 10, 0));
        productOne.displayInfo();
    }
}