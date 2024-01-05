public class Product {
    private String productName;
    private double price;
    private int availableQuantity;
    // Static variables are class-level properties, shared among all instances, with only one copy for the entire class.
    private static int globalReviewCount = 0;

    public Product(String productName, double price, int availableQuantity) {
        this.productName = productName;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    void displayInfo() {
        System.out.println("Product: " + productName + ", Price: $" + price + ", Available quantity: " + availableQuantity);
    }

    // Member class for applying discounts
    class Discount {
        private double discountPercentage;

        public Discount(double discountPercentage) {
            this.discountPercentage = discountPercentage;
        }

        // Applying a discount using both outer and inner class variables, member class has access to outer class's instance variables
        void applyDiscount() {
            // Accessing the price variable of the outer class directly.
            double discountedPrice = price - (price * (discountPercentage / 100));
            System.out.println("Applying a " + discountPercentage + "% discount. Discounted price: $" + discountedPrice);
        }
    }

    // Static nested class for handling product reviews
    static class Review {
        private String comment;
        private int rating;
        // A static variable in an inner class is associated with the inner class, and it is not directly related to instances of the outer class.
        private static int reviewCount;

        public Review(String comment, int rating) {
            this.comment = comment;
            this.rating = rating;
            reviewCount++;
            globalReviewCount++;
        }

        // Static nested classes do not have access to the instance variables and methods of the outer class unless they are static.
        public void displayReview() {
            System.out.println("Review: " + comment + ", Rating: " + rating + ", Total number of reviews: " + globalReviewCount);
        }

        public static int getReviewCount() {
            return reviewCount;
        }

        public static int getGlobalReviewCount() {
            return globalReviewCount;
        }
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void purchase(int quantity) {
        if (quantity <= availableQuantity) {
            System.out.println("Purchased " + quantity + " units of " + productName);
            availableQuantity -= quantity;
        } else {
            System.out.println("Insufficient quantity in stock for " + productName);
        }
    }
}