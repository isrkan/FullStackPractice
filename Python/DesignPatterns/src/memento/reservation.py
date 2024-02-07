from memento.reservation_memento import ReservationMemento

class Reservation:
    def __init__(self, customer_name, party_size, date_time):
        self.customer_name = customer_name
        self.party_size = party_size
        self.date_time = date_time

    def get_customer_name(self):
        return self.customer_name

    def get_party_size(self):
        return self.party_size

    def get_date_time(self):
        return self.date_time

    # Setter method to update the reservation details
    def update_reservation(self, customer_name, party_size, date_time):
        self.customer_name = customer_name
        self.party_size = party_size
        self.date_time = date_time

    # Create a memento to save the current state
    def save(self):
        return ReservationMemento(self.customer_name, self.party_size, self.date_time)

    # Restore the reservation state from a memento
    def restore(self, memento):
        self.customer_name = memento.get_customer_name()
        self.party_size = memento.get_party_size()
        self.date_time = memento.get_date_time()