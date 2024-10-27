## Python data structures

### 1. Lists
`Lists` are an ordered collection in Python, allowing duplicates and supporting dynamic resizing. Lists are versatile and offer a range of operations for adding, removing, and modifying elements.

- **Creating a list**
  ```python
  list_name = [element1, element2, element3]
  ```

- **Adding elements**
  ```python
  list_name.append(element)         # Add element to end
  list_name.insert(index, element)  # Add element at specific index
  ```

- **Accessing elements**
  ```python
  element = list_name[index]
  ```

- **Modifying elements**
  ```python
  list_name[index] = new_element
  ```

- **Removing elements**
  ```python
  list_name.remove(element)       # Remove specific element
  list_name.pop(index)            # Remove element by index
  ```

- **Searching and counting elements**
  ```python
  exists = element in list_name          # Check if element exists
  index = list_name.index(element)       # Find index of element
  count = list_name.count(element)       # Count occurrences of element
  ```

- **List operations**
  ```python
  length = len(list_name)                   # Get length of list
  sorted_list = sorted(list_name)           # Sort list
  reversed_list = list_name[::-1]           # Reverse list
  sublist = list_name[start:end]            # Get a sublist
  list_name[0], list_name[1] = list_name[1], list_name[0]             # Swapping elements
  ```

### 2. Dictionaries
`Dictionaries` store data as key-value pairs, allowing for efficient lookup, addition, and removal of items. Dictionaries are useful when each value in a collection has a unique identifier or key.

- **Creating a dictionary**
  ```python
  dict_name = {key1: value1, key2: value2}
  ```

- **Adding or updating elements**
  ```python
  dict_name[key] = value        # Add new or update existing key-value pair
  ```

- **Accessing values**
  ```python
  value = dict_name[key]        # Access value by key
  value = dict_name.get(key)    # Access value, returns None if key not found
  ```

- **Removing elements**
  ```python
  dict_name.pop(key)            # Remove item by key
  del dict_name[key]            # Alternative way to remove item by key
  ```

- **Iterating over elements**
  ```python
  for key in dict_name.keys():          # Iterate over keys
  for value in dict_name.values():      # Iterate over values
  for key, value in dict_name.items():  # Iterate over key-value pairs
  ```

- **Other dictionary operations**
  ```python
  length = len(dict_name)                   # Get number of items
  keys = list(dict_name.keys())             # Get all keys as list
  values = list(dict_name.values())         # Get all values as list
  ```

### 3. Sets
`Sets` are unordered collections of unique elements. They are used for storing and performing operations on unique data elements, such as finding intersections or differences between collections.

- **Creating a set**
  ```python
  set_name = {element1, element2, element3}
  ```

- **Adding and removing elements**
  ```python
  set_name.add(element)            # Add element to set
  set_name.remove(element)         # Remove element from set
  set_name.discard(element)        # Remove element, does not raise error if missing
  ```

- **Set operations**
  ```python
  union_set = set1.union(set2)                # Union
  intersection_set = set1.intersection(set2)  # Intersection
  difference_set = set1.difference(set2)      # Difference
  symmetric_diff_set = set1.symmetric_difference(set2)  # Symmetric Difference
  ```

- **Other set operations**
  ```python
  length = len(set_name)                   # Get number of elements
  is_subset = set_name.issubset(other_set) # Check if subset
  is_superset = set_name.issuperset(other_set) # Check if superset
  ```

### 4. Tuples
`Tuples` are immutable ordered collections, meaning their elements cannot be changed once assigned. They are useful when a sequence of items should remain constant throughout the program.

- **Creating a tuple**
  ```python
  tuple_name = (element1, element2, element3)
  ```

- **Accessing elements**
  ```python
  element = tuple_name[index]
  ```

- **Concatenating tuples**
  ```python
  new_tuple = tuple1 + tuple2
  ```

- **Slicing tuples**
  ```python
  sub_tuple = tuple_name[start:end]
  ```

- **Unpacking tuple elements**
  ```python
  (first_element, second_element, *remaining_elements) = tuple_name
  ```

- **Other tuple operations**
  ```python
  length = len(tuple_name)                # Get number of elements
  count = tuple_name.count(element)       # Count occurrences of element
  index = tuple_name.index(element)       # Find index of element
  ```

- **Nested tuples**
  Tuples can contain other tuples, useful for representing multi-dimensional data. It can be useful for scenarios such as representing a table, where each inner tuple represents a row.
    - **Creating nested tuples**
    ```python
    nested_tuple = (("Category1", ("SubcategoryA", "SubcategoryB")), ("Category2", ("SubcategoryC", "SubcategoryD")))
    ```
    - **Accessing elements in nested tuples**
    ```python
    main_category = nested_tuple[0][0]             # Access main category
    subcategories = nested_tuple[0][1]             # Access subcategories
    first_subcategory = nested_tuple[0][1][0]      # Access specific subcategory
    ```

### General notes
- **Mutability**: Lists, dictionaries, and sets are mutable (modifiable), while tuples are immutable.
- **Duplicates**: Lists, tuples and dictionaries (keys only) disallow duplicates by design. Sets only store unique elements, and any duplicates will be removed.
- **Ordering**: Lists and tuples maintain insertion order. Sets and dictionaries do not guarantee any specific order in versions prior to Python 3.7 (where dictionaries now maintain insertion order).