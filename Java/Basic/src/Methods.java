public class Methods {

    // Method to calculate the total price and the count of items
    public static double[] calculateTotalPriceAndCount(double[] prices) {
        double total = 0;
        int itemCount = prices.length;
        for (double price : prices) {
            total += price;
        }
        return new double[]{total, itemCount};
    }

    // Method to find the most expensive item
    public static String[] findMostExpensiveItem(String[] items, double[] prices) {
        double maxPrice = 0;
        String mostExpensiveItem = "";
        String expensivePrice = "";
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > maxPrice) {
                maxPrice = prices[i];
                mostExpensiveItem = items[i];
                expensivePrice = Double.toString(prices[i]);
            }
        }
        return new String[]{mostExpensiveItem, expensivePrice};
    }

    // Method to apply a discount to the total price
    public static double applyDiscount(double total, double discountPercentage) {
        return total - (total * (discountPercentage / 100.0));
    }

    // Method to display the receipt
    public static void displayReceipt(String[] items, double[] prices, double total, double discount, int itemCount, String[] mostExpensiveItem) {
        System.out.println("=== Receipt ===");
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i] + ": $" + prices[i]);
        }
        System.out.println("================");
        System.out.println("Price: $" + total);
        System.out.println("Final Price: $" + (discount));
        System.out.println("Number of items: " + itemCount);
        System.out.println("Most expensive item: " + mostExpensiveItem[0] + " ($" + mostExpensiveItem[1] + ")");
    }

    public static void main(String[] args) {
        // Products and prices
        String[] products = {"Bread", "Milk", "Eggs", "Chocolates"};
        double[] prices = {2.5, 1.2, 3.0, 3.5};

        // Calculate the total price and the count of items
        double[] result = calculateTotalPriceAndCount(prices);
        double totalPrice = result[0];
        int itemCount = (int) result[1];
        // Find the most expensive item
        String[] mostExpensiveItem = findMostExpensiveItem(products, prices);

        // Apply a discount of 10%
        double discountedPrice = applyDiscount(totalPrice, 10);

        // Display the receipt
        displayReceipt(products, prices, totalPrice, discountedPrice, itemCount, mostExpensiveItem);
    }
}