import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a list of products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 10));
        products.add(new Product("Smartphone", 20));
        products.add(new Product("Headphones", 30));

        // Process orders using threads extending thread class
        System.out.println("Processing orders using threads extending thread class:");
        for (Product product : products) {
            OrderProcessorThread orderProcessorThread = new OrderProcessorThread(product, 3);
            orderProcessorThread.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Process orders using threads implementing runnable interface
        System.out.println("\nProcessing orders using threads implementing runnable interface:");
        for (Product product : products) {
            OrderProcessorRunnable orderProcessorRunnable = new OrderProcessorRunnable(product, 2);
            Thread thread = new Thread(orderProcessorRunnable);
            thread.start();
        }
    }
}