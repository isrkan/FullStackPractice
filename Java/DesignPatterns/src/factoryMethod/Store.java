package factoryMethod;

// Abstract store class with the factory method
public abstract class Store {
    private String storeName;

    public Store(String storeName) {
        this.storeName = storeName;
    }

    // Factory method to create a worker
    protected abstract Worker createWorker(String workerName);

    public void performBusinessOperation(String workerName) {
        Worker worker = createWorker(workerName);
        System.out.println(storeName + " is open, and the " + worker.getName() + " is working:");
        worker.doWork();
    }
}