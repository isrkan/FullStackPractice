package factoryMethod;

public class GroceryStore extends Store {
    public GroceryStore(String storeName) {
        super(storeName);
    }

    @Override
    protected Worker createWorker(String workerName) {
        // The factory method for a grocery store creates cashiers
        return new Cashier(workerName);
    }
}