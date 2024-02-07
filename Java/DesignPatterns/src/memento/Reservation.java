package memento;

public class Reservation {
    private String customerName;
    private int partySize;
    private String dateTime;

    public Reservation(String customerName, int partySize, String dateTime) {
        this.customerName = customerName;
        this.partySize = partySize;
        this.dateTime = dateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getPartySize() {
        return partySize;
    }

    public String getDateTime() {
        return dateTime;
    }

    // Setter method to update the reservation details
    public void updateReservation(String customerName, int partySize, String dateTime) {
        this.customerName = customerName;
        this.partySize = partySize;
        this.dateTime = dateTime;
    }

    // Create a memento to save the current state
    public ReservationMemento save() {
        return new ReservationMemento(customerName, partySize, dateTime);
    }

    // Restore the reservation state from a memento
    public void restore(ReservationMemento memento) {
        this.customerName = memento.getCustomerName();
        this.partySize = memento.getPartySize();
        this.dateTime = memento.getDateTime();
    }
}