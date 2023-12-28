from product import Product

class ElectronicProduct(Product):
    def __init__(self, productId, name, price, manufacturer, brand, model):
        super().__init__(productId, name, price, manufacturer)
        self._brand = brand
        self._model = model

    # Getter and Setter for the additional field
    def getBrand(self):
        return self._brand

    def setBrand(self, brand):
        self._brand = brand

    def getModel(self):
        return self._model

    def setModel(self, model):
        self._model = model

    # Override the displayInfo method to include additional information
    def displayInfo(self):
        super().displayInfo()
        print(f"Brand: {self._brand}, Model: {self._model}")