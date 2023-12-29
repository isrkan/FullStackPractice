import java.time.LocalDateTime;

public class Main {
    // Polymorphic method calling method on each product
    public static void displayProductInformation(Product[] products) {
        for (Product product : products) {
            product.displayInfo();
        }
    }

    public static void main(String[] args) {
        // Creating objects of the subclasses
        LocalDateTime expirationDate = LocalDateTime.of(2023, 12, 31, 12, 0); // Example expiration date
        FoodProduct pasta = new FoodProduct(1, "Pasta", 1.99, "Osem", expirationDate, true);
        ElectronicProduct laptop = new ElectronicProduct(2, "Mac", 899.99, "HP", "Pavilion", "x360s");
        ClothingProduct shirt = new ClothingProduct(3, "Shirt", 19.99, "Zara", "Medium", "Cotton");

        // Displaying information
        System.out.println("Food product information:");
        pasta.displayInfo();

        System.out.println("\nElectronic product information:");
        laptop.displayInfo();

        System.out.println("\nClothing product information:");
        shirt.displayInfo();

        // Calling the polymorphic method to display information
        Product[] products = {pasta, laptop, shirt};
        System.out.println();
        displayProductInformation(products);
    }
}