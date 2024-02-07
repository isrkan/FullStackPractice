package factoryMethod;

public class Salesperson implements Worker {
    private String name;

    public Salesperson(String name) {
        this.name = name;
    }

    @Override
    public void doWork() {
        System.out.println("Salesperson " + name + " is assisting customers.");
    }

    @Override
    public String getName() {
        return name;
    }
}