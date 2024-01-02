class Smartphone implements ElectronicProduct, RatedProduct {
    private String name;
    private double price;
    private String brand;
    private double rating;

    public Smartphone(String name, double price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.rating = 0.0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void displayInfo() {
        System.out.println("Smartphone: " + name + ", Price: $" + price + ", Brand: " + brand + ", Rating: " + rating + " stars");
    }

    @Override
    public void rateProduct(double rating) {
        if (rating >= 0.0 && rating <= 5.0) {
            this.rating = rating;
            System.out.println("Smartphone rated: " + rating + " stars");
        } else {
            System.out.println("Invalid rating. Please provide a rating between 0 and 5 stars.");
        }
    }

    // Additional method specific to this class
    public void installApp(String appName) {
        System.out.println("Installing " + appName + " on " + brand + " " + name);
    }
}
