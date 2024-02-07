package memento;

// Memento class to hold the reservation state
public class ReservationMemento {
    private final String customerName;
    private final int partySize;
    private final String dateTime;

    public ReservationMemento(String customerName, int partySize, String dateTime) {
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
}