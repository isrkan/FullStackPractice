// Thread that implements the Runnable interface
class OrderProcessorRunnable implements Runnable {
    private final Product product;
    private final int orderQuantity;

    public OrderProcessorRunnable(Product product, int orderQuantity) {
        this.product = product;
        this.orderQuantity = orderQuantity;
    }

    @Override
    public void run() {
        // Simulate a process of processing an order
        product.decreaseQuantity(orderQuantity);
    }
}