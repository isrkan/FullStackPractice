# Memento pattern in Java

**The memento pattern** is a behavioral design pattern that allows an object to save and restore its state without exposing its internal details. It provides a way to keep track of the history of an object’s state, enabling users to revert the object to a previous state when needed. The memento pattern is useful in scenarios where changes are frequent and the option to “undo” or “rollback” changes is necessary, such as in text editors, database transactions, or game states.

In simple terms, the memento pattern allows us to “snapshot” the current state of an object, store that snapshot, and restore it later. This is achieved without violating the principle of encapsulation, as the pattern hides the details of state management from other parts of the program.

## Why use the memento pattern?
The memento pattern is beneficial when:
- **Undo functionality**: It enables us to roll back an object to its previous state.
- **Encapsulation of state**: The pattern preserves encapsulation by storing object state without exposing its internal structure.
- **Simplified state management**: It simplifies state management by keeping snapshots, allowing for easy state transitions.

Some typical applications of the memento pattern include:
- **Undo/redo functionality in editors**: Reverting documents or drawings to previous versions.
- **Game state management**: Saving checkpoints in games to restore a player’s progress.
- **Transaction history in software**: Keeping records of changes in transactional systems.

## Components of the memento pattern
The memento pattern consists of three primary components:
1. **Originator**: The object that contains the state we want to save and restore. This is the object that contains the data or state that might change over time. The originator creates a memento containing its current state and uses it to restore its previous state when needed. The originator has the critical state that we want to save, but it doesn’t expose its internal state directly. Instead, it delegates the saving and restoring of its state to the memento. For example, imagine a text editor. The text editor (the originator) has the text content as its internal state. The editor can create a memento to save the current text, and later restore it when the user wants to undo or redo a change.
2. **Memento**: The memento is a simple object that holds a snapshot of the originator’s internal state at a specific point in time. It is like a "record" of the object’s state that can be saved and restored later. The memento is immutable (i.e., its state cannot be changed once created) and only the originator can create or access its contents, thus ensuring that the internal state of the originator is not exposed. The memento provides a way to encapsulate the state of the originator without giving outside objects direct access to the originator’s internal data. This helps keep the originator’s state private and protected. For example, in the text editor, a memento could store the exact contents of the text at a certain point in time, including things like the cursor position and formatting choices. This memento can be saved when the user wants to undo an action.
3. **Caretaker**: This component manages the mementos by storing and retrieving them as needed. The caretaker doesn’t know the contents of the memento — it only holds and manages them for future restoration. It never modifies the contents of the memento—it just stores and retrieves it. The caretaker manages the history of the originator’s state, keeping track of all the saved mementos. It decouples the responsibility of managing the state from the object that owns the state. For example, the caretaker could be a history manager in the text editor that stores multiple mementos for different versions of the text. The caretaker might manage an undo stack that holds mementos, allowing the user to undo changes one by one.

## Implementing the memento pattern in Java
Here’s a general structure of how to implement the memento pattern in Java:

### Step 1: Define the originator
The originator is the object whose state we want to save and restore. It contains the state and is responsible for creating and restoring mementos. It has methods to save its current state to a memento and restore its state from a given memento.
```java
package memento;

public class Originator {
    private String state;

    // Updates the state of the originator
    public void setState(String state) {
        this.state = state;
        System.out.println("State set to: " + state);
    }

    // Saves the current state to a new memento
    public Memento save() {
        return new Memento(state);  // Create a memento with the current state
    }

    // Restores the originator's state from a given memento
    public void restore(Memento memento) {
        this.state = memento.getState();  // Set state to the memento's saved state
        System.out.println("State restored to: " + state);
    }
}
```

### Step 2: Define the memento
The memento class captures the state of the originator at a specific time. It’s immutable (once it is created, its state cannot be changed) and doesn’t provide public methods to modify its contents by external objects, thus ensuring encapsulation.
```java
package memento;

public class Memento {
    private final String state;

    // Constructor to create a memento with a specific state
    public Memento(String state) {
        this.state = state;
    }

    // Returns the saved state
    public String getState() {
        return state;
    }
}
```

### Step 3: Define the caretaker
The caretaker is responsible for storing and managing the mementos. It has methods to add a new memento and retrieve the last saved memento for restoration. Importantly, the caretaker does not know or modify the contents of the memento; it simply stores and retrieves them.
```java
package memento;

import java.util.Stack;

public class Caretaker {
    private final Stack<Memento> history = new Stack<>();  // Stack to store mementos

    // Stores a new memento
    public void saveState(Memento memento) {
        history.push(memento);  // Save the memento to the history stack
    }

    // Retrieves the most recent memento (undo operation)
    public Memento undo() {
        return !history.isEmpty() ? history.pop() : null;  // Pop the last saved memento
    }
}
```

### Step 4: Implement the main program
In the main program, create an instance of the originator, manage its state, and use the caretaker to save and restore states as needed.
```java
package memento;

public class Main {
    public static void main(String[] args) {
        // Create an originator instance
        Originator originator = new Originator();
        // Create a caretaker to manage the mementos
        Caretaker caretaker = new Caretaker();

        // Set and save initial state
        originator.setState("State 1");
        caretaker.saveState(originator.save());

        // Change state and save it
        originator.setState("State 2");
        caretaker.saveState(originator.save());

        // Change state again
        originator.setState("State 3");

        // Undo the last state change (restore to "State 2")
        originator.restore(caretaker.undo());

        // Undo another state change (restore to "State 1")
        originator.restore(caretaker.undo());
    }
}
```