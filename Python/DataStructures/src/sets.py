def sets():

    # Set to store retail store types
    store_types = {"Electronics", "Clothing", "Grocery", "Home furnishings", "Sporting goods"}
    # Displaying all store types
    print("\nRetail store types:")
    for store_type in store_types:
        print("- " + store_type)

    # Try to add a duplicate store type
    store_types.add("Clothing")
    # Check if a store type exists and remove it
    type_to_remove = "Electronics"
    if type_to_remove in store_types:
        store_types.remove(type_to_remove)
    print("\nRetail store types after attempting to add a duplicate and removing another store type:")
    for store_type in store_types:
        print("- " + store_type)

    # Create a new set and add store types from another set
    new_store_types = set(store_types)
    new_store_types.add("Books")
    new_store_types.discard("Clothing")
    print("\nNew set of store types:")
    for store_type in new_store_types:
        print("- " + store_type)
    # Get the total number of store types
    print("Total number of store types:", len(new_store_types))

    # Intersection of sets
    intersection_result = store_types.intersection(new_store_types)
    print("\nIntersection of sets:")
    for store_type in intersection_result:
        print("- " + store_type)

    # Convert set to array
    store_array = list(store_types)

    # Create another set for symmetric difference
    additional_store_types = {"Books", "Clothing", "Toys", "Electronics", "Grocery"}
    symmetric_difference_result = store_types.symmetric_difference(additional_store_types)
    print("\nSymmetric difference of sets:")
    for store_type in symmetric_difference_result:
        print("- " + store_type)

    # Create a frozen set and try to add elements from a frozen set (will raise an error)
    # Once a frozen set is created, we cannot add, remove, or modify its elements
    frozen_set = frozenset(new_store_types)
    try:
        frozen_set.add("Shoes")
    except AttributeError as e:
        print(f"Error: {e}")


if __name__ == '__main__':
    sets()