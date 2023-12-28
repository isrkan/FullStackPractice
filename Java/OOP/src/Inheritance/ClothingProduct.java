class ClothingProduct extends Product {
    // Additional field
    private String size;
    private String material;

    public ClothingProduct(int productId, String name, double price, String manufacturer, String size, String material) {
        super(productId, name, price, manufacturer); // Call the superclass constructor
        this.size = size;
        this.material = material;
    }

    // Getter and Setter for the additional field
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    // Override the displayInfo method to include additional information
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call the superclass method
        System.out.println("Size: " + size + ", Material: " + material);
    }
}
