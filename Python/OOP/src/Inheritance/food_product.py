from product import Product

class FoodProduct(Product):
    def __init__(self, productId, name, price, manufacturer, expirationDate, isOrganic):
        super().__init__(productId, name, price, manufacturer)
        self._expirationDate = expirationDate
        self._isOrganic = isOrganic

    # Getter and Setter for the additional field
    def getExpirationDate(self):
        return self._expirationDate

    def setExpirationDate(self, expirationDate):
        self._expirationDate = expirationDate

    def isOrganic(self):
        return self._isOrganic

    def setOrganic(self, organic):
        self._isOrganic = organic

    # Override the displayInfo method to include additional information
    def displayInfo(self):
        super().displayInfo()
        print(f"Expiration Date: {self._expirationDate}, Organic: {self._isOrganic}")