# Memento class to hold the reservation state
class ReservationMemento:
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