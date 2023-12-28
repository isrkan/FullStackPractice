public class Product {

    // Fields
    private int productId;
    private String name;
    private double price;
    private String manufacturer;

    public Product(int productId, String name, double price, String manufacturer) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    // Getter and Setter methods
    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void displayInfo() {
        System.out.println("Product ID: " + productId + ", Product: " + name + ", Price: $" + price
                + ", Manufacturer: " + (manufacturer != null ? manufacturer : "N/A"));
    }
}
