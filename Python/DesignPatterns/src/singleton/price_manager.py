# Singleton class to manage device prices
class PriceManager:
    # Class level variable to hold the singleton instance
    __instance = None

    def __init__(self, manager_name, employee_number):
        self.manager_name = manager_name
        self.employee_number = employee_number

    @staticmethod
    def get_instance(manager_name, employee_number):
        if PriceManager.__instance is None:
            # Lazy initialization: Create the instance if it's None
            PriceManager.__instance = PriceManager(manager_name, employee_number)
        return PriceManager.__instance

    # Method to update the price of a device
    def update_device_price(self, device, new_price):
        device.set_price(new_price)
        print("Price for {} updated to: ${:.2f}".format(device.get_name(), new_price))