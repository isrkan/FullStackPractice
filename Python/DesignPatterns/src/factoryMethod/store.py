from abc import ABC, abstractmethod

# Abstract store class with the factory method
class Store(ABC):
    def __init__(self, store_name):
        self.store_name = store_name

    # Factory method to create a worker
    @abstractmethod
    def create_worker(self, worker_name):
        pass

    def perform_business_operation(self, worker_name):
        worker = self.create_worker(worker_name)
        print(self.store_name + " is open, and the " + worker.get_name() + " is working:")
        worker.do_work()