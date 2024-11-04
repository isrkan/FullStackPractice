# Threads in Python

**Threads** allow Python programs to handle multiple tasks simultaneously, which is particularly useful for performing background operations or tasks that might block the main program flow, such as file I/O or network requests. A **thread** is essentially a lightweight, separate sequence of execution within a program, and by using threads, a program can perform several operations concurrently, thus improving efficiency and responsiveness. Python supports threading with the `threading` module, which provides tools to create, manage, and synchronize threads.

## Key concepts in Python threading

### 1. Creating threads
In Python, we can create a thread in two primary ways:
1. **Subclassing the `Thread` class**: Define a new class that extends `Thread` and override its `run()` method to specify the thread's task. After creating a thread object, call its `start()` method to begin execution.

2. **Using the `Thread` class with a target function**: We can directly pass a target function (or a callable object) to the `Thread` constructor. This approach avoids subclassing and is generally simpler.

### Example
```python
import threading

# Using a function as the target
def print_numbers():
    for i in range(5):
        print(i)

# Using a subclass of Thread
class MyThread(threading.Thread):
    def run(self):
        print("Thread running")

# Start threads
thread1 = threading.Thread(target=print_numbers)
thread2 = MyThread()

thread1.start()
thread2.start()
```

### 2. Running threads
To start a thread, call its `start()` method. This launches the thread and calls its `run()` method, enabling the task to execute in parallel with other threads.

### 3. Thread synchronization and locks
When multiple threads access shared resources, **synchronization** is crucial to avoid conflicts like race conditions, where threads might modify data in inconsistent ways. Python provides **locks** to synchronize access to shared resources, ensuring that only one thread can access a critical section of code at a time.

#### Example of using a lock
```python
lock = threading.Lock()

# Critical section wrapped with Lock
def critical_section():
    with lock:
        # Safe access to shared resources here
        pass
```

Using a `Lock` ensures that only one thread at a time can enter the critical section, making operations on shared resources thread-safe.

### 4. Thread safety and shared data
In a multi-threaded environment, ensure **thread safety** by limiting access to shared data. Some common ways to maintain thread safety are:
- **Using locks**: Use a `Lock` to synchronize access to shared data.
- **Avoiding shared state**: Reduce the amount of data shared across threads to limit potential conflicts.
- **Atomic operations**: Some operations are atomic in nature and can be used safely without explicit locks, but always test carefully to ensure consistency.

### 5. The thread lifecycle
A Python thread goes through several states in its lifecycle:
- **New**: The thread is created but has not yet started (`start()` has not been called).
- **Runnable**: The thread is ready to run, awaiting CPU time.
- **Running**: The thread is actively executing code.
- **Blocked**: The thread is waiting for a resource (e.g., waiting to acquire a lock).
- **Terminated**: The thread has completed execution.

### 6. Daemon vs. non-daemon threads
A **daemon thread** is a background thread that automatically terminates when the main program exits. Non-daemon threads (the default) continue running until they complete, even if the main program has finished.

To set a thread as a daemon, use:
```python
thread = threading.Thread(target=some_function)
thread.setDaemon(True)
thread.start()
```

Daemon threads are often used for background tasks, such as monitoring or housekeeping.

### 7. Using `join()` to wait for threads
The `join()` method makes the main program wait for a thread to finish execution before continuing. This is useful when a thread’s completion is necessary before moving forward.
```python
thread = threading.Thread(target=some_function)
thread.start()
thread.join()  # Wait for the thread to finish before proceeding
```