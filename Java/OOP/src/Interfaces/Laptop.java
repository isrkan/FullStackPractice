class Laptop implements ElectronicProduct {
    protected String name;
    private double price;
    private String brand;
    private int ram;

    public Laptop(String name, double price, String brand, int ram) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.ram = ram;
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
    public void displayInfo() {
        System.out.println("Laptop: " + name + ", Price: $" + price + ", Brand: " + brand+ ", RAM memory: " + ram + "GB");
    }

    // Additional method specific to this class
    public void upgrade(int additionalRam) {
        this.ram += additionalRam;
        System.out.println("Upgraded " + brand + " " + name + "'s RAM to " + ram + "GB");
    }
}
