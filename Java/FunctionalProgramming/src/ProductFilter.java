@FunctionalInterface
interface ProductFilter {
    boolean test(Product product);
}