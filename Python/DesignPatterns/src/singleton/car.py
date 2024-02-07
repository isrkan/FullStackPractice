from singleton.device import Device

class Car(Device):
    def __init__(self, name, price, model):
        super().__init__(name, price)
        self.model = model

    def get_model(self):
        return self.model