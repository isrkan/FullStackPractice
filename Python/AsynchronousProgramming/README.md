# Asynchronous programming in Python

Asynchronous programming is a programming paradigm designed to handle tasks that may take varying amounts of time to complete, such as network requests, file I/O, or waiting on user input. Unlike synchronous programming, where tasks are executed one at a time and wait for each other to finish, asynchronous programming allows multiple tasks to run concurrently. This means that while one task is waiting (e.g., for data to load), another task can proceed, resulting in improved efficiency and responsiveness in applications.

In Python, asynchronous programming is primarily enabled by the `asyncio` library, which provides tools to write asynchronous code using **coroutines** and **tasks**.

## Key concepts

### 1. **Coroutines**
A **coroutine** is a special type of function that can be paused and resumed. In Python, a coroutine function is defined with the `async` keyword. When called, it returns a coroutine object that can be awaited to pause execution until the coroutine completes. 

- **Defining a coroutine**: A coroutine function is created using `async def`, and it can include `await` expressions to pause and wait for other asynchronous operations.
  ```python
  import asyncio

  async def example_coroutine():
      print("Start")
      await asyncio.sleep(1)  # Pauses for 1 second
      print("End")
  ```

In this example, `await asyncio.sleep(1)` pauses the coroutine for 1 second, allowing other tasks to run during that time.

### 2. **The `await` Keyword**
The `await` keyword is used inside asynchronous functions to pause execution until the awaited coroutine or asynchronous operation completes. This is the mechanism that allows asynchronous code to yield control back to the event loop and lets other tasks run in the meantime.

- **Using `await`**: The `await` keyword can only be used inside an `async` function. It can be used to wait on coroutines, such as `await asyncio.sleep(2)` to pause for 2 seconds, or to wait on the result of other asynchronous functions.
  ```python
  async def my_function():
      print("Task started")
      await asyncio.sleep(2)  # Simulates an asynchronous wait
      print("Task completed")
  ```

Here, the function pauses at `await asyncio.sleep(2)` for 2 seconds, but other tasks can run during that wait.

### 3. **Tasks and `asyncio.create_task()`**
A **task** is a coroutine that has been scheduled to run on the event loop. Using `asyncio.create_task()` allows us to run multiple coroutines concurrently. Each task is scheduled independently, meaning that tasks do not wait for each other unless explicitly awaited.

- **Creating tasks**: We use `asyncio.create_task()` to create tasks that run concurrently. This allows tasks to progress while others may be waiting.
  ```python
  async def task1():
      await asyncio.sleep(2)
      print("Task 1 completed")

  async def task2():
      await asyncio.sleep(1)
      print("Task 2 completed")

  async def main():
      t1 = asyncio.create_task(task1())
      t2 = asyncio.create_task(task2())
      await t1  # Wait for task 1 to finish
      await t2  # Wait for task 2 to finish

  asyncio.run(main())
  ```

In this example, `task1` and `task2` run concurrently. The program waits for both tasks to complete, but they don’t have to finish one after the other.

### 4. **`asyncio.gather()` for concurrent execution**
`asyncio.gather()` is used to run multiple coroutines concurrently and wait for all of them to complete. It is particularly useful when we need to start a set of coroutines and only proceed after all have finished. 

- **Gathering tasks**: We can pass a list of tasks to `asyncio.gather()` to manage them as a single operation that completes when all the tasks are done.

  ```python
  async def main():
      await asyncio.gather(task1(), task2(), task3())
  ```

In this case, both `task1()` and `task2()` will run at the same time, and the program will wait for both to finish before continuing.

### 5. **Event loop**
The **event loop** is the core of asynchronous programming in Python. It is responsible for scheduling and managing the execution of asynchronous tasks and coroutines. The loop continues running until all tasks are completed or canceled. The `asyncio.run()` function is used to start the event loop and execute an async function from the main entry point.

## Example structure of an asynchronous Python application
Here is a general overview of how to structure an asynchronous application in Python:

1. **Define Asynchronous Functions**: Create functions with `async def` to handle asynchronous operations (e.g., processing orders, preparing data). These functions should contain any asynchronous operations using `await` where necessary.
2. **Create tasks**: Use `asyncio.create_task()` to define tasks that need to run concurrently.
3. **Gather or await tasks**: Use `asyncio.gather()` to wait on multiple tasks if we want them to complete before moving forward, or individually `await` tasks if they depend on one another.
4. **Run the event loop**: Use `asyncio.run()` to start the asynchronous workflow from the main function, executing the program's main coroutine.

### Example
```python
import asyncio

# Example async function
async def fetch_data():
    await asyncio.sleep(1)  # Simulate a network delay
    print("Data fetched")

# Example async function
async def process_data():
    await asyncio.sleep(2)  # Simulate data processing delay
    print("Data processed")

# Main function to run tasks concurrently
async def main():
    task1 = asyncio.create_task(fetch_data())
    task2 = asyncio.create_task(process_data())
    await asyncio.gather(task1, task2)  # Wait for all tasks to finish

# Run the main function
if __name__ == "__main__":
    asyncio.run(main())
```

## Asynchronous programming use cases
Asynchronous programming is ideal for tasks that involve waiting without blocking other processes, such as:
- **Web scraping**: Requesting data from multiple sources simultaneously.
- **Database operations**: Fetching and storing data from databases without blocking.
- **File I/O operations**: Reading and writing large files without stopping other tasks.
- **Network calls**: Making API requests to various endpoints at once.

## Asynchronous programming vs. threading

### Asynchronous programming (`asyncio`)
- **Single-threaded**: Asynchronous programming with `asyncio` runs on a single thread, relying on the event loop to manage task scheduling. This avoids the overhead of creating multiple threads, making it more efficient for I/O-bound tasks that involve waiting for responses.
- **Ideal for I/O-bound tasks**: Async programming is well-suited for tasks like network calls, database operations, and other I/O-bound work where the program spends time waiting.
- **Task switching**: The event loop switches between tasks whenever a task is waiting, making efficient use of resources without blocking other operations.

### Threading (`threading` library)
- **Multi-threaded**: The `threading` library uses multiple threads within the same process. Each thread runs independently and can perform CPU-bound tasks concurrently, which is beneficial for parallelism.
- **Better for CPU-bound tasks**: Threading is often more effective for CPU-bound tasks that need parallel execution of computations, as each thread can run on a separate CPU core (though the Global Interpreter Lock (GIL) can limit true parallelism).
- **Resource overhead**: Each thread has its own memory stack, so creating multiple threads can introduce memory overhead. Additionally, threads can sometimes require complex synchronization mechanisms to avoid conflicts (e.g., race conditions).

### Choosing between async programming and threading
The choice depends on the type of task:
- **I/O-bound tasks (waiting for network/database responses)**: `asyncio` is generally a better fit, as it allows a single thread to manage multiple I/O operations efficiently.
- **CPU-bound tasks (e.g., computations)**: Threading (or multiprocessing) may be better suited for CPU-intensive tasks where parallel processing can provide real speed improvements.
