from product import Product

def main():

    product = Product("Laptop", 1000, 50)

    # Using nested class
    discount = product.Discount(product, 15)
    product.display_info()
    discount.apply_discount()

    product.purchase(10)
    print("Available quantity:", product.get_available_quantity())

    # Using another nested class
    product_review1 = product.Review(product, "Great product, highly recommended!", 5)
    product_review1.display_review()
    product_review2 = product.Review(product, "Not satisfied with the performance.", 2)
    product_review2.display_review()

    # Display review count
    print("Review Count:", product.Review.get_review_count())
    print("Global Review Count:", product.Review.get_global_review_count())


if __name__ == '__main__':
    main()