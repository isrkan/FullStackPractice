# Singleton class to manage car-related operations
class CarManager:
    # Singleton instance variable
    __instance = None

    # Constructor not really needed in Python

    # Static method to get the singleton instance
    @staticmethod
    def get_instance():
        if CarManager.__instance is None:
            CarManager.__instance = CarManager()
        return CarManager.__instance

    def calculate_total_price_with_tax(self, car, tax_rate):
        total_price = car.get_price() * (1 + tax_rate / 100)
        print("Total price for {} {} with {:.2f}% tax: ${:.2f}".format(car.get_name(), car.get_model(), tax_rate, total_price))

    def process_car_order(self, car):
        print("Processing order for {} {} with a base price of ${:.2f}".format(car.get_name(), car.get_model(), car.get_price()))