package observerPattern;

// Concrete Observer representing a chef
public class Chef implements OrderObserver {
    private String name;

    public Chef(String name) {
        this.name = name;
    }

    @Override
    public void update(String order) {
        System.out.println("Chef " + name + " received order: " + order);
    }

    public void handle(String order) {
        System.out.println("Chef " + name + " handle order: " + order);
    }
}