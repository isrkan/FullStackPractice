class Product:
    def __init__(self, productId, name, price, manufacturer):
        self._productId = productId
        self._name = name
        self._price = price
        self._manufacturer = manufacturer

    # Getter and Setter methods
    def getProductId(self):
        return self._productId

    def getName(self):
        return self._name

    def setName(self, name):
        self._name = name

    def getPrice(self):
        return self._price

    def setPrice(self, price):
        self._price = price

    def getManufacturer(self):
        return self._manufacturer

    def setManufacturer(self, manufacturer):
        self._manufacturer = manufacturer

    def displayInfo(self):
        print(f"Product ID: {self._productId}, Product: {self._name}, Price: ${self._price}, Manufacturer: {self._manufacturer if self._manufacturer else 'N/A'}")