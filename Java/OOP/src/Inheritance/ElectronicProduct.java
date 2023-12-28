class ElectronicProduct extends Product {
    // Additional field
    private String brand;
    private String model;

    public ElectronicProduct(int productId, String name, double price, String manufacturer, String brand, String model) {
        super(productId, name, price, manufacturer); // Call the superclass constructor
        this.brand = brand;
        this.model = model;
    }

    // Getter and Setter for the additional field
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

    // Override the displayInfo method to include additional information
    @Override
        public void displayInfo() {
            super.displayInfo(); // Call the superclass method
            System.out.println("Brand: " + brand + ", Model: " + model);
        }
}
