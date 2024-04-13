package observerPattern;

// Concrete Observer representing a delivery person
public class DeliveryPerson implements OrderObserver {
    private String name;

    public DeliveryPerson(String name) {
        this.name = name;
    }

    @Override
    public void update(String order) {
        System.out.println("Delivery person " + name + " received order: " + order);
    }

    public void handle(String order) {
        System.out.println("Delivery person " + name + " handle order: " + order);
    }
}