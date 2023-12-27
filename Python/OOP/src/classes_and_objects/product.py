class Product:
    def __init__(self, name, price, product_id, manufacturer, production_date):
        self.name = name
        self.price = price
        self.product_id = product_id
        self.manufacturer = manufacturer
        self.production_date = production_date

    def display_info(self):
        print(f"Product ID: {self.product_id}, Product: {self.name}, Price: ${self.price}, "
              f"Manufacturer: {self.manufacturer if self.manufacturer is not None else 'N/A'}, "
              f"Production date: {self.production_date if self.production_date is not None else 'N/A'}")
