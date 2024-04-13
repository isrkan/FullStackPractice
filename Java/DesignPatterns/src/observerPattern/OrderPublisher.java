package observerPattern;

// Interface for the Publisher (Subject)
interface OrderPublisher {
    void addObserver(OrderObserver observer);
    void notifyObservers(String order);
}