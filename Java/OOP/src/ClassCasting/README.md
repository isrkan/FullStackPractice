# Class casting in Java

In Java, **class casting** allows us to treat an object as an instance of a different class, enabling flexibility in how we handle objects. Class casting is used to **upcast** and **downcast** objects within an inheritance hierarchy, providing control over how much specific functionality or detail of a class we expose at any given point. This concept is crucial in scenarios where we want to work with objects at different levels of their class hierarchy, either abstracting them into general types or specializing them back to their concrete class.

By utilizing class casting carefully in Java, we can achieve a balance between flexible abstraction and specific functionality, empowering them to write code that is both modular and dynamically adaptable.
- **Polymorphism and flexibility**: Class casting enhances **polymorphism**, allowing different subclasses to be used interchangeably through their superclass type. Upcasting hides subclass details, promoting a unified interface, while downcasting reveals specific behaviors, giving access to subclass functionality as needed.
- **Dynamic behavior**: Class casting, especially when combined with conditional checks, allows code to dynamically handle various object types at runtime. This is common when working with collections or frameworks that treat objects as superclass types but need to perform subclass-specific operations in certain cases.

### When to use class casting
- **Abstraction and generalization**: By upcasting, we can treat objects as instances of a more general superclass, which is useful when working with collections or methods that should handle various subclass types in a unified way.
- **Specialization and extended behavior**: Through downcasting, we can access specific functionalities and fields of a subclass that may not be visible or accessible from the superclass perspective.


## Core concepts and syntax

### Upcasting
**Upcasting** is casting an object of a subclass to its superclass type. This process is **implicit** in Java, meaning it doesn't require explicit syntax because every subclass object is also inherently a superclass object.

#### Upcasting example
In the example below, an instance of `GroceryProduct` (subclass) is assigned to a reference of type `Product` (superclass):
```java
Product product = new GroceryProduct("Apple", 1.99, "Fruits", 100);
product.displayInfo();
```

- This upcasted `product` reference will only have access to `Product`'s methods, even though the actual object is of type `GroceryProduct`.

#### Benefits of upcasting
- Simplifies handling of multiple types by treating them as a common type.
- Useful for creating more generalized, flexible methods that operate on a superclass type.

### Downcasting
**Downcasting** is the process of casting a superclass reference back to its specific subclass type, allowing access to subclass-specific methods and fields. Downcasting must be done **explicitly** and with caution, as it assumes the object is truly of the type being cast.

#### Downcasting example
To access `GroceryProduct`-specific methods, we can downcast `product` back to `GroceryProduct`:
```java
if (product instanceof GroceryProduct) {
    GroceryProduct groceryProduct = (GroceryProduct) product;
    groceryProduct.restock(50); // Calls a method specific to GroceryProduct
}
```

- The `instanceof` operator ensures that the `product` object is actually an instance of `GroceryProduct` before casting, which prevents `ClassCastException` at runtime.

#### Benefits and considerations of downcasting
- Allows specific operations unique to subclasses while maintaining type safety.
- Always check type compatibility with `instanceof` to prevent runtime errors.