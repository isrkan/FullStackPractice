from factoryMethod.store import Store
from factoryMethod.cashier import Cashier

class GroceryStore(Store):
    def __init__(self, store_name):
        super().__init__(store_name)

    def create_worker(self, worker_name):
        # The factory method for a grocery store creates cashiers
        return Cashier(worker_name)