public class Product {
    private String name;
    private int quantity;

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity() {
        this.quantity = quantity;
    }

    public void decreaseQuantity(int amount) {
        // synchronized helps avoid potential data inconsistencies when multiple threads access shared resources concurrently
        synchronized (this) {
            if (amount > 0 && amount <= quantity) {
                quantity -= amount;
                System.out.println("Ordered " + amount + " units of " + name + ". Remaining quantity: " + quantity);
            } else {
                System.out.println("Invalid order quantity for " + name);
            }
        }
    }
}
