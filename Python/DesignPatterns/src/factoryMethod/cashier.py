from factoryMethod.worker import Worker

class Cashier(Worker):
    def __init__(self, name):
        self.name = name

    def do_work(self):
        print("Cashier " + self.name + " is processing transactions.")

    def get_name(self):
        return self.name