from factoryMethod.store import Store
from factoryMethod.salesperson import Salesperson

class ElectronicsStore(Store):
    def __init__(self, store_name):
        super().__init__(store_name)

    def create_worker(self, worker_name):
        # The factory method for an electronics store creates salespersons
        return Salesperson(worker_name)