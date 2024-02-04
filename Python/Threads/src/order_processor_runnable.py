# In Python, there's no explicit Runnable interface as in Java.
# In Python, any class with a run method can be considered runnable.
class OrderProcessorRunnable:
    def __init__(self, product, order_quantity):
        self.product = product
        self.order_quantity = order_quantity

    def run(self):
        # Simulate a process of processing an order
        self.product.decrease_quantity(self.order_quantity)