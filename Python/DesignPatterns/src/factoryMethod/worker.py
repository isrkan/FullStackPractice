from abc import ABC, abstractmethod

class Worker(ABC):
    @abstractmethod
    def get_name(self):
        pass

    @abstractmethod
    def do_work(self):
        pass