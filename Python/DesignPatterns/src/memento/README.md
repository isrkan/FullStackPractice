# Memento pattern in Python

The **memento pattern** is a behavioral design pattern that allows an object to save and restore its internal state without exposing the details of its implementation. This pattern is particularly useful when an object’s state changes frequently, and it’s important to have the ability to revert to previous states. It’s commonly used to implement "undo" functionality in applications like text editors, graphics editors, or any software where users might want to revert to a previous configuration.

In simple terms, the memento pattern enables us to create a “snapshot” of an object’s current state, which can be saved and restored later. This is done without exposing the object's internal structure, thus maintaining encapsulation.

## Why use the memento pattern?
- **Undo and redo operations**: Enables users to revert or move back and forth between states.
- **Encapsulation of internal state**: Preserves the integrity of the object's state by not exposing internal fields.
- **Simplified state management**: Makes managing complex state changes more straightforward by providing a reliable way to save and restore.


## Components of the memento pattern
The Memento Pattern consists of three main components:
1. **Originator**: This is the object whose state needs to be saved and restored. The originator creates a memento with its current state and uses it to revert to previous states when required. 
2. **Memento**: This class represents the “snapshot” of the originator's state. It contains the data of the originator’s state at a specific point in time but does not expose this data to other objects.
3. **Caretaker**: The caretaker is responsible for storing and retrieving mementos. It knows when to request a new memento from the originator and when to restore the originator’s state. The caretaker does not, however, modify or access the memento’s data directly.

### Example structure in Python
Below is an example structure of how to implement the memento pattern in Python.

### Step 1: Define the originator
The **originator** is the object whose state we want to save and restore. It contains the data that might change over time and provides methods to create a memento (saving its current state) and to restore its state from a given memento.
```python
from memento import Memento

class Originator:
    def __init__(self, state):
        self.state = state

    def set_state(self, state):
        self.state = state
        print(f"State set to: {self.state}")

    def save_to_memento(self):
        return Memento(self.state)

    def restore_from_memento(self, memento):
        self.state = memento.get_state()
        print(f"State restored to: {self.state}")
```

### Step 2: Define the memento
The **Memento** class captures the state of the originator at a specific point in time. The memento class does not allow external objects to modify its contents, preserving the encapsulation of the originator's data.
```python
class Memento:
    def __init__(self, state):
        self._state = state

    def get_state(self):
        return self._state
```

### Step 3: Define the caretaker
The **caretaker** manages the mementos and does not modify the memento's state. It simply stores the mementos and retrieves them for the originator when needed.
```python
class Caretaker:
    def __init__(self):
        self._history = []

    def save_state(self, memento):
        self._history.append(memento)

    def undo(self):
        if self._history:
            return self._history.pop()
        return None
```

### Step 4: Implementing the main program
The main program creates an originator instance and a caretaker to manage the mementos. The originator’s state can be modified and saved, and the caretaker allows undoing those changes by restoring previous states.
```python
from originator import Originator
from caretaker import Caretaker

def main():
    # Create originator and caretaker
    originator = Originator("Initial State")
    caretaker = Caretaker()

    # Save initial state
    caretaker.save_state(originator.save_to_memento())

    # Change state and save again
    originator.set_state("State 1")
    caretaker.save_state(originator.save_to_memento())

    # Change state again
    originator.set_state("State 2")

    # Undo to previous states
    originator.restore_from_memento(caretaker.undo())
    originator.restore_from_memento(caretaker.undo())

if __name__ == '__main__':
    main()
```