public class Main {
    public static void main(String[] args) {

        // Create instances of different products

        // products.GroceryProduct implements the products.Product interface
        GroceryProduct apple = new GroceryProduct("Apple", 1.99, 100);
        apple.displayInfo();
        apple.orderMoreStock(50);
        System.out.println();

        // products.Laptop implements the products.ElectronicProduct interface. products.ElectronicProduct implements the products.Product interface
        Laptop laptop = new Laptop("products.Laptop", 899.99, "Dell", 16);
        laptop.displayInfo();
        laptop.upgrade(16);
        System.out.println();

        // products.TShirt implements the products.ClothingProduct interface. products.ClothingProduct implements the products.Product interface
        TShirt tShirt = new TShirt("Casual T-Shirt", 19.99, "Medium", "Cotton");
        tShirt.displayInfo();
        tShirt.customize("Small", "Polyester");
        System.out.println();

        // products.PremiumLaptop inherits from products.Laptop class. products.Laptop implements the products.ElectronicProduct interface. products.ElectronicProduct implements the products.Product interface
        PremiumLaptop premiumLaptop = new PremiumLaptop("Premium laptop", 1499.99, "HP", 32, true, 1);
        premiumLaptop.displayInfo();
        premiumLaptop.activateWarranty(3);
        System.out.println();

        // products.RatedGroceryProduct inherits from products.GroceryProduct and implements the products.RatedProduct interface. products.GroceryProduct implements the products.Product interface. products.RatedProduct implements the products.Product interface
        RatedGroceryProduct ratedApple = new RatedGroceryProduct("Rated apple", 2.49, 50);
        ratedApple.displayInfo();
        ratedApple.rateProduct(4.5);
        ratedApple.suggestImprovements("The apples were fresh and delicious, but it would be great to have more variety.");
        System.out.println();

        // products.Smartphone implements from the products.ElectronicProduct and products.RatedProduct interfaces. products.ElectronicProduct and products.RatedProduct implement the products.Product interface
        Smartphone smartphone = new Smartphone("products.Smartphone", 699.99, "Samsung");
        smartphone.displayInfo();
        smartphone.installApp("Facebook");
    }
}