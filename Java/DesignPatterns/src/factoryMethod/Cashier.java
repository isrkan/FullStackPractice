package factoryMethod;

public class Cashier implements Worker {
    private String name;

    public Cashier(String name) {
        this.name = name;
    }
    @Override
    public void doWork() {
        System.out.println("Cashier " + name + " is processing transactions.");
    }

    @Override
    public String getName() {
        return name;
    }
}