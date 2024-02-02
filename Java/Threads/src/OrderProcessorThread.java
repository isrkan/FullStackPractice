// Thread that extends the Thread class
class OrderProcessorThread extends Thread {
    private final Product product;
    private final int orderQuantity;

    public OrderProcessorThread(Product product, int orderQuantity) {
        this.product = product;
        this.orderQuantity = orderQuantity;
    }

    @Override
    public void run() {
        // Simulate a process of processing an order
        product.decreaseQuantity(orderQuantity);
    }
}