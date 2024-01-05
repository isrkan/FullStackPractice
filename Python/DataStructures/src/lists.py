def list():
    # List to store product names
    product_names = ["Laptop", "Smartphone", "Headphones", "Tablet"]

    # Displaying all products by iterating through the list
    print("\nCurrent products:")
    for product in product_names:
        print("- " + product)

    # Replace an element at a specific index
    product_names[2] = "Watch"
    # Deleting a product
    product_name = "Smartphone"
    if product_name in product_names:
        product_names.remove(product_name)
    print("\nCurrent products:")
    for product in product_names:
        print("- " + product)

    # Searching for a product
    product_name = "Laptop"
    if product_name in product_names:
        print(product_name + " is available.")

    # Calculate the number of products
    print("Total number of products:", len(product_names))

    # Check if the list is empty
    if not product_names:
        print("No products available.")

    # Get an element at a specific index
    print("The first element is:", product_names[0])
    # Find the index of an element
    print("Tablet found at index", product_names.index("Tablet"))

    # Create a sublist from a list
    sublist = product_names[0:2]
    print("\nSublist of products:")
    for product in sublist:
        print("- " + product)

    # Clone a list
    copy_product_names = product_names.copy()
    # Sort elements in the list
    copy_product_names.sort()
    # Join elements into a single string
    print("\nSorted products:", ", ".join(copy_product_names))

    # Reverse the order of elements and swap 2 elements
    copy_product_names.reverse()
    product_names[0], product_names[1] = product_names[1], product_names[0]
    print("\nReversed products:", ", ".join(copy_product_names))

    # Check if the list contains all elements in a collection
    check_list = ["Watch", "Tablet"]
    if all(item in product_names for item in check_list):
        print("\nThe list contains all elements in the check_list.")


if __name__ == '__main__':
    list()