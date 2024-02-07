# Class to manage the history of reservations
class ReservationHistory:
    def __init__(self):
        self.history = []

    # Save a reservation state to the history
    def save_reservation(self, reservation_memento):
        self.history.append(reservation_memento)

    # Restore the most recent reservation state from the history
    def undo(self):
        if self.history:
            return self.history.pop()
        return None