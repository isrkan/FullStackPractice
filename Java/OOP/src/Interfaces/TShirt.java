class TShirt implements ClothingProduct {
    private String name;
    private double price;
    private String size;
    private String material;

    public TShirt(String name, double price, String size, String material) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.material = material;
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
    public String getSize() {
        return size;
    }

    @Override
    public void displayInfo() {
        System.out.println("T-Shirt: " + name + ", Price: $" + price + ", Size: " + size + ", Material: " + material);
    }

    // Additional method specific to this class
    public void customize(String newSize, String newMaterial) {
        System.out.println("Customizing " + name + ": Size - " + newSize + ", Material - " + newMaterial);
        this.size = newSize;
        this.material = newMaterial;
    }
}
