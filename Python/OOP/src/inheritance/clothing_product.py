from product import Product

class ClothingProduct(Product):
    def __init__(self, productId, name, price, manufacturer, size, material):
        super().__init__(productId, name, price, manufacturer)
        self._size = size
        self._material = material

    # Getter and Setter for the additional field
    def getSize(self):
        return self._size

    def setSize(self, size):
        self._size = size

    def getMaterial(self):
        return self._material

    def setMaterial(self, material):
        self._material = material

    # Override the displayInfo method to include additional information
    def displayInfo(self):
        super().displayInfo()
        print(f"Size: {self._size}, Material: {self._material}")