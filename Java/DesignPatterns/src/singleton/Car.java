package singleton;

public class Car extends Device {
    private final String model;

    public Car(String name, double price, String model) {
        super(name, price);
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}