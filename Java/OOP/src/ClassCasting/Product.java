public class Product {
    String name;
    double price;
    double discountPrice;
    String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.discountPrice = price; // Initially set to the regular price
        this.category = category;
    }

    public void displayInfo() {
        System.out.println("Product: " + name + ", Category: " + category + ", Price: $" + price);
        if (discountPrice < price){
            System.out.println("Discounted Price: $" + discountPrice);
        }
    }

    public void setPrice(double newPrice, boolean keepDiscount) {
        System.out.println("Updating regular price of " + name + " to $" + newPrice);
        if (keepDiscount && (this.price != this.discountPrice)){
            double discountAmount = (1 - (this.discountPrice / this.price)) * 100;
            this.price = newPrice;
            applyDiscount(discountAmount);
        } else {
            this.price = newPrice;
            updateDiscountPrice();
        }
    }

    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0) {
            System.out.println("Applying a " + discountPercentage + "% discount to " + name);
            this.discountPrice = this.price - (this.price * (discountPercentage / 100));
        }
    }

    private void updateDiscountPrice() {
        // Update discount price when regular price changes
        this.discountPrice = this.price;
    }
}