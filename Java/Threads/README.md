# Threads in Java

**Threads** are a fundamental concept in Java that allow multiple tasks or processes to run concurrently, improving the responsiveness and efficiency of programs. A **thread** is essentially a lightweight, independent path of execution within a program, allowing operations to overlap. For example, a program can handle user input, database operations, and calculations simultaneously, improving efficiency and performance.

Java’s threading capabilities are particularly useful when dealing with tasks that may block (such as file I/O or network operations) or when a program needs to manage multiple tasks simultaneously.

## Key threading concepts in Java

### 1. Creating threads
Java provides two main ways to create and run threads, each with its specific use cases and characteristics:
- **Extending the `Thread` class**: This approach involves creating a subclass of the `Thread` class and overriding its `run()` method with the task that the thread should execute. Each instance of this class represents a separate thread.
    ```java
    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread running by extending Thread class");
        }
    }

    public class Main {
        public static void main(String[] args) {
            MyThread thread = new MyThread();
            thread.start();  // Starts the thread and calls run()
        }
    }
    ```
- **Implementing the `Runnable` interface**: Another way to create a thread is to implement the `Runnable` interface, which also requires defining a `run()` method. This approach is considered more flexible, as the same `Runnable` instance can be used with multiple threads if needed, and the class can extend other classes as well.
    ```java
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread running by implementing Runnable interface");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }
    ```

### 2. Running threads
Once a thread is created, whether by extending `Thread` or implementing `Runnable`, it needs to be started. This is done by calling the `start()` method on the `Thread` instance. Calling `start()` initializes a new call stack for the thread and invokes the `run()` method.

### 3. Synchronization and shared resources
When multiple threads access shared resources or modify shared data, there is a risk of **race conditions**—inconsistent data states due to concurrent access. **Synchronization** in Java is used to control access to shared resources, ensuring that only one thread can access a particular piece of code at a time. This is achieved using the `synchronized` keyword, which can be applied to methods or blocks of code to lock the resource for the thread that currently owns the lock.

Example of synchronizing a method:
```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();
    }
}
```

#### Key points about synchronization:
- **Synchronized block**: Only the code within a synchronized block is protected, allowing finer control over the code that needs to be synchronized.
- **Instance lock**: The synchronized keyword locks the current instance of the class, ensuring that only one thread can access the synchronized block or method on a given object.
- **Atomicity**: Synchronization ensures that data changes appear as atomic (indivisible) operations, preventing data inconsistencies.

### 4. Thread lifecycle
Each thread goes through several states during its lifecycle:
- **New**: A thread is created but has not yet started.
- **Runnable**: The thread has been started and is eligible for execution, waiting to be selected by the scheduler.
- **Blocked**: The thread is waiting for a monitor lock (synchronization).
- **Waiting**: The thread is waiting indefinitely for another thread to perform a specific action.
- **Timed waiting**: The thread is waiting for a specified time period.
- **Terminated**: The thread has completed its execution.

### 5. Thread safety
**Thread safety** refers to the property of a program or object to function correctly in a concurrent environment, even when accessed by multiple threads simultaneously. Key practices for ensuring thread safety include:
- **Using synchronization**: Helps avoid data inconsistencies when multiple threads modify shared data.
- **Limiting shared data**: Reduces the chances of conflicts by minimizing the data that needs to be shared between threads.
- **Using atomic classes**: Java provides a set of atomic classes (e.g., `AtomicInteger`, `AtomicBoolean`) in the `java.util.concurrent.atomic` package that perform atomic operations, making them inherently thread-safe without needing explicit synchronization.
```java
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
```

### 6. Thread priority and scheduling
Java threads have a priority that influences the order in which threads are scheduled for execution. Thread priorities are integers ranging from `MIN_PRIORITY` (1) to `MAX_PRIORITY` (10), with `NORM_PRIORITY` (5) being the default. The Java Virtual Machine (JVM) uses these priorities as hints to the thread scheduler, but thread scheduling is ultimately determined by the JVM and the underlying OS.