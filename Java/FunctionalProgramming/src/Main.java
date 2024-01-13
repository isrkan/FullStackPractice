import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create a list of products
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200),
                new Product("Smartphone", 500),
                new Product("Headphones", 80),
                new Product("Tablet", 300),
                new Product("Mouse", 25)
        );

        System.out.println("Original Products: " + products);

        // Example 1: Using lambda with forEach to print each product's information
        System.out.print("Using Lambda with forEach to display product info: ");
        products.forEach(product -> System.out.print(product + " | "));

        // Example 2: Apply a discount using traditional anonymous class
        ProductOperation discountOperationAnonymous = new ProductOperation() {
            @Override
            public double perform(double price, double discount) {
                return price - (price * discount / 100);
            }
        };
        displayDiscountedPrices(products, discountOperationAnonymous);

        // Example 3: Apply a discount using lambda expression
        ProductOperation discountOperationLambda = (price, discount) -> price - (price * discount / 100);
        displayDiscountedPrices(products, discountOperationLambda);

        // Example 4: Use forEach() with lambda expression to apply a discount
        System.out.println("Discounted Prices using forEach():");
        products.forEach(product -> {
            double discountedPrice = discountOperationLambda.perform(product.getPrice(), 10);
            System.out.println(product.getName() + ": $" + discountedPrice);
        });
        System.out.println();

        // Example 5: Apply a bulk discount using lambda expression with a block
        ProductOperation bulkDiscountOperation = (price, discount) -> {
            if (price > 500) {
                return price - (price * (discount + 5) / 100);
            } else {
                return price - (price * discount / 100);
            }
        };
        displayDiscountedPrices(products, bulkDiscountOperation);

        // Example 6: Filter products using lambda expression
        ProductFilter expensiveProductFilter = product -> product.getPrice() > 500;
        displayFilteredProducts(products, expensiveProductFilter);

        // Example 7: Sort products using lambda expression
        ProductSorter priceSorter = (product1, product2) -> Double.compare(product1.getPrice(), product2.getPrice());
        displaySortedProducts(products, "Sorted products by price:", priceSorter);
        ProductSorter nameSorter = (product1, product2) -> product1.getName().compareTo(product2.getName());
        displaySortedProducts(products, "Sorted products by name:", nameSorter);


        // Example 8: Use streams to get the total price of all products
        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("Total price of all products: $" + totalPrice);
        System.out.println();

        // Example 9: Use streams to find the most expensive product
        Product mostExpensiveProduct = products.stream()
                .max((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .orElse(null);
        if (mostExpensiveProduct != null) {
            System.out.println("Most expensive product: " + mostExpensiveProduct.getName());
        }
        System.out.println();

        // Example 10: Use streams and lambda with filter to find expensive products
        System.out.println("\nExpensive products:");
        products.stream()
                .filter(product -> product.getPrice() > 200)
                .forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));
    }

    private static void displayDiscountedPrices(List<Product> products, ProductOperation operation) {
        System.out.println("Discounted Prices:");
        for (Product product : products) {
            double discountedPrice = operation.perform(product.getPrice(), 10);
            System.out.println(product.getName() + ": $" + discountedPrice);
        }
        System.out.println();
    }

    // Display products that satisfy the filter condition
    private static void displayFilteredProducts(List<Product> products, ProductFilter filter) {
        System.out.println("Filtered Products:");
        for (Product product : products) {
            if (filter.test(product)) {
                System.out.println(product.getName() + ": $" + product.getPrice());
            }
        }
        System.out.println();
    }

    // Display products sorted based on the provided comparator
    private static void displaySortedProducts(List<Product> products, String message, ProductSorter sorter) {
        products.sort(sorter);
        System.out.println(message);
        for (Product product : products) {
            System.out.println(product.getName() + ": $" + product.getPrice());
        }
        System.out.println();
    }
}