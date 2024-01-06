def tuples():
    # Tuple to store retail store types
    store_types = ("Electronics", "Clothing", "Grocery", "Home Furnishings", "Sporting Goods", "Grocery")

    # Displaying all store types
    print("\nRetail store types:")
    for store_type in store_types:
        print("- " + store_type)
    # Accessing individual elements in the tuple
    print("First store type: " + store_types[0])
    print("Last store type: " + store_types[-1])

    # Concatenating tuples
    new_store_types = ("Books", "Jewelry")
    all_store_types = store_types + new_store_types
    print("\nAll store types after adding new ones:")
    for store_type in all_store_types:
        print("- " + store_type)

    # Length of the tuple
    print("\nTotal number of store types:", len(all_store_types))

    # Checking if a store type exists in the tuple
    check_store_type = "Grocery"
    print("Is '{}' an existing store type? {}".format(check_store_type, check_store_type in all_store_types))

    # Slicing the tuple
    print("\nSlicing the tuple:")
    selected_store_types = all_store_types[1:4]
    for store_type in selected_store_types:
        print("- " + store_type)

    # Unpacking tuple elements
    first_store, second_store, *rest = all_store_types
    print("\nUnpacking tuple elements:")
    print("First store: " + first_store)
    print("Second store: " + second_store)
    print("Rest of the stores:", rest)

    # Count occurrences of a specific store type
    print("\nNumber of 'Grocery' occurrences:", all_store_types.count("Grocery"))

    # Try to update a tuple element (this will raise an error)
    try:
        all_store_types[0] = "Toys"
    except TypeError as e:
        print("\nError:", e)

    # Nested tuples
    nested_tuple = (("Electronics", 120), ("Clothing", 80), ("Grocery", 200))
    print("\nNested tuple:")
    for store_type, quantity in nested_tuple:
        print("- {}: {} products".format(store_type, quantity))

    # Another nested tuple
    nested_tuple = (
        ("Electronics", 120, ("Laptops", "Smartphones")),
        ("Clothing", 80, ("Shirts", "Dresses")),
        ("Grocery", 200, ("Fruits", "Vegetables"))
    )
    print("\nAnother nested tuple:")
    for store_type, quantity, products in nested_tuple:
        print("- {}: {} products".format(store_type, quantity))
        print("  - Product categories:")
        for product_category in products:
            print("    - " + product_category)


if __name__ == '__main__':
    tuples()