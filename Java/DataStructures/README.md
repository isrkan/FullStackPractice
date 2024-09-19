## Java data structures

### 1. ArrayList
`ArrayList` is a resizable array implementation of the `List` interface in Java. It allows dynamic array operations such as adding, removing, and accessing elements. Use `ArrayList` when we need fast random access to elements and when resizing the list dynamically is required. It's ideal for scenarios where elements are frequently accessed by index, but not ideal for frequent insertions or deletions from the middle of the list due to its internal array structure.

#### Creating an ArrayList
```java
import java.util.ArrayList;

ArrayList<Type> listName = new ArrayList<>();
```

#### Adding elements
```java
listName.add(element);
listName.add(index, element);
```

#### Accessing elements
```java
Type element = listName.get(index);
```

#### Modifying elements
```java
listName.set(index, newElement);
```

#### Removing elements
```java
listName.remove(index);
listName.remove(element);
```

#### Iterating over elements
```java
for (Type element : listName) {
    // Process element
}
```

#### Size and emptiness
```java
int size = listName.size();
boolean isEmpty = listName.isEmpty();
```

#### Searching for elements
```java
boolean contains = listName.contains(element);
int index = listName.indexOf(element);
```

#### Sublist
```java
ArrayList<Type> subList = new ArrayList<>(listName.subList(startIndex, endIndex));
```

#### Sorting and reversing
```java
import java.util.Collections;

Collections.sort(listName);
Collections.reverse(listName);
```

#### Converting to array
```java
Type[] array = new Type[listName.size()];
listName.toArray(array);
```

#### Using iterators
```java
import java.util.Iterator;

Iterator<Type> iterator = listName.iterator();
while (iterator.hasNext()) {
    Type element = iterator.next();
    // Process element
}
```

### 2. LinkedList
`LinkedList` is a doubly-linked list implementation in Java. It provides sequential access, and operations like insertion and deletion are more efficient compared to `ArrayList`. Use `LinkedList` when we need fast insertions and deletions at both ends or at arbitrary positions within the list. It is efficient for sequential access but slower for random access compared to `ArrayList`.

#### Creating a LinkedList
```java
import java.util.LinkedList;

LinkedList<Type> listName = new LinkedList<>();
```

#### Adding elements
```java
listName.add(element);
listName.add(index, element);
listName.addFirst(element);
listName.addLast(element);
```

#### Accessing elements
```java
Type element = listName.get(index);
Type firstElement = listName.getFirst();
Type lastElement = listName.getLast();
```

#### Modifying elements
```java
listName.set(index, newElement);
```

#### Removing elements
```java
listName.remove(index);
listName.remove(element);
listName.removeFirst();
listName.removeLast();
```

#### Iterating over elements
```java
for (Type element : listName) {
    // Process element
}
```

#### Size and emptiness
```java
int size = listName.size();
boolean isEmpty = listName.isEmpty();
```

#### Searching for elements
```java
boolean contains = listName.contains(element);
int index = listName.indexOf(element);
```

#### Sorting and reversing
```java
import java.util.Collections;

Collections.sort(listName);
Collections.reverse(listName);
```

### 3. HashMap
`HashMap` is a hash table-based implementation of the `Map` interface. It allows key-value pairs, where each key maps to one value. Use `HashMap` when we need to store key-value pairs and retrieve values quickly using keys. It's efficient for search, insert, and delete operations but does not maintain any specific order of the elements. It's ideal for situations where fast lookup and updates by key are required.

#### Creating a HashMap
```java
import java.util.HashMap;

HashMap<KeyType, ValueType> mapName = new HashMap<>();
```

#### Adding elements
```java
mapName.put(key, value);
```

#### Accessing elements
```java
ValueType value = mapName.get(key);
```

#### Modifying elements
```java
mapName.put(key, newValue);
```

#### Removing elements
```java
mapName.remove(key);
```

#### Iterating over elements
```java
for (KeyType key : mapName.keySet()) {
    ValueType value = mapName.get(key);
    // Process key and value
}
```

#### Size and emptiness
```java
int size = mapName.size();
boolean isEmpty = mapName.isEmpty();
```

#### Searching for keys or values
```java
boolean containsKey = mapName.containsKey(key);
boolean containsValue = mapName.containsValue(value);
```

#### Computing values
```java
mapName.computeIfPresent(key, (k, v) -> newValue);
mapName.computeIfAbsent(key, k -> defaultValue);
```

### 4. HashSet
`HashSet` is a hash table-based implementation of the `Set` interface. It contains unique elements and provides fast access and retrieval. `HashSet` removes duplicates and has no guaranteed order. Use `HashSet` when we need to maintain a collection of unique elements, with no duplicates allowed. It provides fast access and retrieval and is ideal for scenarios where the uniqueness of elements is required, such as a list of unique IDs or items in a collection.

#### Creating a HashSet
```java
import java.util.HashSet;

HashSet<Type> setName = new HashSet<>();
```

#### Adding elements
```java
setName.add(element);
```

#### Removing elements
```java
setName.remove(element);
```

#### Iterating over elements
```java
for (Type element : setName) {
    // Process element
}
```

#### Size and emptiness
```java
int size = setName.size();
boolean isEmpty = setName.isEmpty();
```

#### Searching for elements
```java
boolean contains = setName.contains(element);
```

#### Set operations
```java
HashSet<Type> otherSet = new HashSet<>();
setName.addAll(otherSet); // Union
setName.retainAll(otherSet); // Intersection
setName.removeAll(otherSet); // Difference
```

#### Converting to array
```java
Type[] array = new Type[setName.size()];
setName.toArray(array);
```