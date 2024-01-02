class PremiumLaptop extends Laptop {
    private boolean warranty;
    private int warrantyYears;

    public PremiumLaptop(String name, double price, String brand, int ram, boolean warranty, int warrantyYears) {
        super(name, price, brand, ram);
        this.warranty = warranty;
        this.warrantyYears = warrantyYears;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Warranty Years: " + warrantyYears);
    }

    // Additional method specific to this class
    public void activateWarranty(int warrantyYears) {
        System.out.println("Activating " + warrantyYears + " more years of warranty for " + name);
        this.warrantyYears += warrantyYears;
    }

}
