from dependencyInjection.product import Product
from dependencyInjection.shopping_cart import ShoppingCart
from singleton.device import Device
from singleton.price_manager import PriceManager
from singleton.car import Car
from singleton.car_manager import CarManager
from factoryMethod.grocery_store import GroceryStore
from factoryMethod.electronics_store import ElectronicsStore
from memento.reservation import Reservation
from memento.reservation_history import ReservationHistory

def main():

    ## Dependency injection (DI)
    # Create a product instance
    laptop = Product("Laptop", 1200)
    # Create a shopping cart using constructor injection
    cart1 = ShoppingCart(laptop)
    cart1.display_product_info()
    total_price1 = cart1.calculate_total_price(10)
    print("Total Price (with 10% discount): ${:.2f}".format(total_price1))
    # Create another shopping cart
    cart2 = ShoppingCart(Product("Smartphone", 500))
    # Use method injection to set the product for the second cart
    cart2.set_product(laptop)
    cart2.display_product_info()
    total_price2 = cart2.calculate_total_price(5)
    print("Total Price (with 5% discount): ${:.2f}".format(total_price2))
    print()


    ## Singleton
    # Example 1: Create a product instance
    printer = Device("Printer", 200)
    # Use the singleton PriceManager to update the product price
    price_manager = PriceManager.get_instance("Israel Israeli", 12345)
    price_manager.update_device_price(printer, 300)
    # Try to create another instance of PriceManager (should be the same instance)
    another_price_manager = PriceManager.get_instance("Michael Michaeli", 12346)
    print("Are the two PriceManager instances the same?", price_manager is another_price_manager)

    # Example 2: Create a car instance
    luxury_car = Car("BMW", 50000, "XYZ")
    # Use the Singleton CarManager to perform car-related operations
    car_manager = CarManager.get_instance()
    # Calculate the total price with tax
    car_manager.calculate_total_price_with_tax(luxury_car, 8.5)
    # Process the car order
    car_manager.process_car_order(luxury_car)
    print()


    ## Factory method
    # Open a Grocery Store and perform business operations
    grocery_store = GroceryStore("SuperDeal")
    grocery_store.perform_business_operation("David")
    # Open an Electronics Store and perform business operations
    electronics_store = ElectronicsStore("ElectroZone")
    electronics_store.perform_business_operation("Ben")
    print()


    ## Memento method
    # Create a new reservation
    reservation = Reservation("Nathan", 4, "2024-02-10 19:00")
    print("Initial reservation: {} - party size: {} - date & time: {}".format(reservation.get_customer_name(), reservation.get_party_size(), reservation.get_date_time()))
    # Save the initial state
    history = ReservationHistory()
    history.save_reservation(reservation.save())
    # Update the reservation
    reservation.update_reservation("Jacob", 6, "2024-02-10 20:00")
    print("Updated reservation: {} - party size: {} - date & time: {}".format(reservation.get_customer_name(), reservation.get_party_size(), reservation.get_date_time()))
    history.save_reservation(reservation.save())
    # Update the reservation again
    reservation.update_reservation("Leonard", 8, "2024-02-10 21:00")
    print("Updated reservation: {} - party size: {} - date & time: {}".format(reservation.get_customer_name(), reservation.get_party_size(), reservation.get_date_time()))
    # Undo the last update
    previous_state = history.undo()
    if previous_state is not None:
        reservation.restore(previous_state)
        print("Reservation after undo: {} - party size: {} - date & time: {}".format(reservation.get_customer_name(), reservation.get_party_size(), reservation.get_date_time()))
    else:
        print("No previous state available to undo.")


if __name__ == '__main__':
    main()