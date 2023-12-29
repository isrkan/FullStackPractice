from product import Product

class ElectronicsProduct(Product):
    def __init__(self, name, price, category, brand, warranty_years):
        super().__init__(name, price, category)
        self.brand = brand
        self.warranty_years = warranty_years

    def display_info(self):
        super().display_info()
        print(f"Brand: {self.brand}, Warranty Years: {self.warranty_years}")

    def extend_warranty(self, additional_years):
        print(f"Extending warranty of {self.brand} {self.name} by {additional_years} years")
        self.warranty_years += additional_years