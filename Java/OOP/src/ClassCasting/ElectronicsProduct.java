class ElectronicsProduct extends Product {
    String brand;
    int warrantyYears;

    public ElectronicsProduct(String name, double price, String category, String brand, int warrantyYears) {
        super(name, price, category);
        this.brand = brand;
        this.warrantyYears = warrantyYears;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Brand: " + brand + ", Warranty Years: " + warrantyYears);
    }

    public void extendWarranty(int additionalYears) {
        System.out.println("Extending warranty of " + brand + " " + name + " by " + additionalYears + " years");
        this.warrantyYears += additionalYears;
    }
}