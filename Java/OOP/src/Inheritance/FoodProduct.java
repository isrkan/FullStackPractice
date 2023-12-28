import java.time.LocalDateTime;

class FoodProduct extends Product {
    // Additional field
    private LocalDateTime expirationDate;
    private boolean isOrganic;

    public FoodProduct(int productId, String name, double price, String manufacturer, LocalDateTime expirationDate, boolean isOrganic) {
        super(productId, name, price, manufacturer); // Call the superclass constructor
        this.expirationDate = expirationDate;
        this.isOrganic = isOrganic;
    }

    // Getter and Setter for the additional field
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public void setPerishable(boolean organic) {
        isOrganic = organic;
    }

    // Override the displayInfo method to include additional information
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call the superclass method
        System.out.println("Expiration Date: " + expirationDate + ", Organic: " + isOrganic);
    }
}
