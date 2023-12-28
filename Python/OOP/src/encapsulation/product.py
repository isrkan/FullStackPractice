from datetime import datetime

class Product:
    def __init__(self, name, price, product_id, manufacturer, production_date):
        self._name = None  # Private field with leading underscore
        self._price = 0.0
        self._product_id = product_id
        self._manufacturer = None
        self._production_date = None
        # Use setter methods to enforce validation
        self.set_name(name)
        self.set_price(price)
        self.set_manufacturer(manufacturer)
        self.set_production_date(production_date)

    def get_name(self):
        return self.name

    def get_price(self):
        return self.price

    def get_product_id(self):
        return self.product_id

    def get_manufacturer(self):
        return self.manufacturer

    def get_production_date(self):
        return self.production_date

    def set_name(self, name):
        if name is not None and name.strip():
            self._name = name
        else:
            print("Invalid name")

    def set_price(self, price):
        if price >= 0:
            self._price = price
        else:
            print("Invalid price. Price cannot be negative.")

    def set_manufacturer(self, manufacturer):
        self._manufacturer = manufacturer

    def set_production_date(self, production_date):
        if production_date is not None and production_date < datetime.now():
            self._production_date = production_date
        else:
            print("Invalid production date. Production date cannot be in the future.")

    def display_info(self):
        print(f"Product ID: {self._product_id}, Product: {self._name}, Price: ${self._price}, "
              f"Manufacturer: {self._manufacturer if self._manufacturer is not None else 'N/A'}, "
              f"Production date: {self._production_date if self._production_date is not None else 'N/A'}")