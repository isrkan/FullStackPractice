package factoryMethod;

public class ElectronicsStore extends Store {
    public ElectronicsStore(String storeName) {
        super(storeName);
    }

    @Override
    protected Worker createWorker(String workerName) {
        // The factory method for an electronics store creates salespersons
        return new Salesperson(workerName);
    }
}