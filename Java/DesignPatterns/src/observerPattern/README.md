# Observer pattern in Java

**The observer pattern** is a behavioral design pattern that defines a one-to-many relationship between objects. When an object (called the "subject" or "publisher") changes state, all its dependent objects (called "observers" or "subscribers") are automatically notified and updated. This pattern is commonly used to implement distributed event-handling systems.

In simple terms, the observer pattern allows an object to send updates to multiple other objects without needing to know any details about them. This promotes loose coupling, as the subject does not depend on the details of the observers, and vice versa.

## Why Use the observer pattern?
The observer pattern is particularly useful when:
- **There is a need for a one-to-many dependency**: A single object needs to notify multiple observers of changes.
- **Loose coupling is required**: The subject and observers are decoupled, making the system more flexible and easier to extend.
- **Dynamic updates are necessary**: The observers can dynamically register or unregister from the subject, enabling or disabling updates on the fly.

Some typical applications of the observer pattern include:
- **Event-driven programming**: Reacting to user actions (like button clicks).
- **Data binding**: Automatically updating the user interface when the underlying data changes.
- **Messaging systems**: Sending notifications or messages to multiple receivers in distributed applications.

## Components of the observer pattern
In Java, the observer pattern involves several key components:
1. **Subject (publisher)**: The subject is the object that holds the main data or state. It maintains a list of all observers that are interested in being notified about changes to this state. It provides methods for adding, removing, and notifying observers about changes. When a change occurs (for example, data is updated), it notifies all registered observers by calling their `update()` method. For example, think of a weather station that collects temperature data. The weather station is the subject because it collects the data and notifies others when the data changes.
2. **Observer (subscriber)**: The observer is an interface or abstract class that defines an `update()` method. Concrete observers implement this interface and define how they should respond when they are notified by the subject. It must define the `update()` method that gets called when the subject changes. For example, in the weather station example, a temperature display or a weather app could be an observer. It listens for changes in the temperature and updates its display when the temperature changes.
3. **Concrete subject**: This is a concrete implementation of the subject. It holds the actual data or state that is of interest to observers and notifies them when its state changes. It keeps track of its state, which is usually of interest to its observers. When its state changes, it triggers the `update()` method on all the observers, notifying them of the change. For example, the weather station in our example is a concrete subject. It might track the current temperature, humidity, and pressure. When any of these values change, it notifies the registered observers (like the display or the app).
4. **Concrete observer**: Concrete observers implement the observer interface and define specific behaviors when they are notified by the subject. It defines how it responds when it is notified by the subject (i.e., how it reacts to changes in the subject’s state). It implements the `update()` method to define the specific behavior that should happen when it gets notified by the subject. It usually updates its own state or performs some action based on the state change of the subject. For example, a weather display screen is a concrete observer in this case. When the temperature in the weather station changes, the display updates itself with the new temperature value.

## Implementing the observer pattern in Java
Here’s a general structure of the observer pattern in Java:

### Step 1: Define the observer interface
The observer interface declares the `update()` method, which will be called by the subject when it notifies observers of a change.
```java
package observerPattern;

public interface Observer {
    void update(String message);
}
```

### Step 2: Define the concrete observers
Concrete observers implement the `Observer` interface. Each observer defines its own response to notifications it receives from the subject.
```java
package observerPattern;

public class ConcreteObserverA implements Observer {
    private String name;

    public ConcreteObserverA(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received update: " + message);
    }
}

public class ConcreteObserverB implements Observer {
    private String name;

    public ConcreteObserverB(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received update: " + message);
    }
}
```

### Step 3: Define the subject interface
The subject interface declares methods for adding, removing, and notifying observers. This provides a standard way for observers to subscribe to or unsubscribe from updates.
```java
package observerPattern;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
```

### Step 4: Define the concrete subject
The concrete subject maintains a list of observers and notifies them of any relevant changes in its state.
```java
package observerPattern;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers(state);
    }

    public String getState() {
        return state;
    }
}
```

In this example, the `ConcreteSubject` manages the state and notifies all registered observers whenever the state changes by calling their `update` methods.

### Step 5: Implement the main program
In the main program, create instances of the subject and observers, then register the observers with the subject. When the subject’s state changes, it will notify each observer automatically.
```java
package observerPattern;

public class Main {
    public static void main(String[] args) {
        // Create the subject
        ConcreteSubject subject = new ConcreteSubject();

        // Create some observers
        Observer observerA = new ConcreteObserverA("Observer A");
        Observer observerB = new ConcreteObserverB("Observer B");

        // Register the observers with the subject
        subject.addObserver(observerA);
        subject.addObserver(observerB);

        // Change the state of the subject and notify observers
        subject.setState("State 1");
        subject.setState("State 2");
    }
}
```

## Key benefits of using the observer pattern
- **Decoupled design**: The subject and observers are loosely coupled, making it easy to add or remove observers without modifying the subject.
- **Real-time updates**: Observers are automatically updated whenever the subject’s state changes, providing real-time responsiveness.
- **Scalability**: New observers can be added without requiring changes to existing code, making it easy to scale the system.