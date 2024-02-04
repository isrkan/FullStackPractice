import threading
import time
from product import Product
from order_processor_thread import OrderProcessorThread
from order_processor_runnable import OrderProcessorRunnable

def main():
    # Create a list of products
    products = [
        Product("Laptop", 10),
        Product("Smartphone", 20),
        Product("Headphones", 30)
    ]

    # Process orders using threads subclassing Thread class and overriding run method
    print("Processing orders using threads extending Thread class:")
    for product in products:
        order_processor_thread = OrderProcessorThread(product, 3)
        order_processor_thread.start()

    time.sleep(1)

    # Process orders using threads passing target function to Thread constructor
    print("\nProcessing orders using threads implementing Runnable interface:")
    for product in products:
        order_processor_runnable = OrderProcessorRunnable(product, 2)
        thread = threading.Thread(target=order_processor_runnable.run)
        thread.start()


if __name__ == "__main__":
    main()