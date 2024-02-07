package memento;
import java.util.Stack;

// Class to manage the history of reservations
public class ReservationHistory {
    private final Stack<ReservationMemento> history = new Stack<>();

    // Save a reservation state to the history
    public void saveReservation(ReservationMemento memento) {
        history.push(memento);
    }

    // Restore the most recent reservation state from the history
    public ReservationMemento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}