import threading

# Thread that extends the Thread class
class OrderProcessorThread(threading.Thread):
    def __init__(self, product, order_quantity):
        super().__init__()
        self.product = product
        self.order_quantity = order_quantity

    def run(self):
        # Simulate a process of processing an order
        self.product.decrease_quantity(self.order_quantity)