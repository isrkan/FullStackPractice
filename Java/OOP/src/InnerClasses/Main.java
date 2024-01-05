public class Main {
    public static void main(String[] args) {

        Product product = new Product("Laptop", 1000, 50);

        // Using member class
        Product.Discount discount = product.new Discount(15);
        product.displayInfo();
        discount.applyDiscount();

        product.purchase(10);
        System.out.println("Available quantity: " + product.getAvailableQuantity());

        // Using static nested class
        Product.Review productReview1 = new Product.Review("Great product, highly recommended!", 5);
        productReview1.displayReview();
        Product.Review productReview2 = new Product.Review("Not satisfied with the performance.", 2);
        productReview2.displayReview();

        System.out.println("Review Count: " + Product.Review.getReviewCount());
        System.out.println("Global Review Count: " + Product.Review.getGlobalReviewCount());
    }
}