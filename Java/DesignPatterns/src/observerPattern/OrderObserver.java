package observerPattern;

// Interface for the Observer (Subscriber)
interface OrderObserver {
    void update(String order);
    void handle(String order);
}