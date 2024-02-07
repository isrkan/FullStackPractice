from factoryMethod.worker import Worker

class Salesperson(Worker):
    def __init__(self, name):
        self.name = name

    def do_work(self):
        print("Salesperson " + self.name + " is assisting customers.")

    def get_name(self):
        return self.name