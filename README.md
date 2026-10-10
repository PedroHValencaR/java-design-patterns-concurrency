# Java Design Patterns & Concurrency

A comprehensive Java project demonstrating core Object-Oriented Design Patterns (GoF) and Multi-threaded Concurrent Programming, based on university Software Engineering coursework (*Object-Oriented Programming in Java* and *Software Design Patterns*).

---

## 🏛️ Project Architecture & Design Patterns

### 1. Structural / Architectural Patterns
* **DAO (Data Access Object) Pattern:**
  * `StudentDao.java`: Contract interface decoupling business logic from underlying data persistence.
  * `InMemoryStudentDao.java`: Thread-safe relational mock persistence implementation supporting full CRUD operations.
  * `StudentNotFoundException.java`: Custom checked exception hierarchy for graceful error recovery.

### 2. Creational Patterns
* **Factory Method Pattern (`NotificationFactory`):**
  * Decouples object instantiation for notification channels (`EmailNotification`, `SmsNotification`).
* **Thread-Safe Singleton Pattern (`DatabaseConnectionPool`):**
  * Implements double-checked locking with `volatile` state to ensure a single, synchronized database connection pool instance across concurrent threads.

### 3. Concurrency & Parallel Execution
* **Multithreading & Thread Synchronization (`ConcurrentTaskProcessor`):**
  * Employs Java `ExecutorService`, thread synchronization, and atomic counters to safely process parallel workloads without race conditions.

---

## 📂 Project Structure

```text
src/main/java/com/pedro/
├── Main.java                             # Execution entrypoint demonstrating all modules
├── patterns/
│   ├── dao/
│   │   ├── Student.java                  # Encapsulated domain entity
│   │   ├── StudentDao.java               # DAO Interface contract
│   │   └── InMemoryStudentDao.java       # Thread-safe in-memory DAO implementation
│   ├── factory/
│   │   ├── Notification.java             # Product interface
│   │   ├── EmailNotification.java        # Concrete product
│   │   ├── SmsNotification.java          # Concrete product
│   │   └── NotificationFactory.java      # Factory instantiation method
│   └── singleton/
│       └── DatabaseConnectionPool.java   # Thread-safe Double-Checked Locking Singleton
├── concurrency/
│   ├── ConcurrentTaskProcessor.java     # Thread pool management and synchronization
│   └── WorkerTask.java                   # Runnable worker simulating async workloads
└── exceptions/
    └── StudentNotFoundException.java     # Domain-specific exception handling
```

---

## 🚀 How to Compile & Run

### Prerequisites
* Java Development Kit (JDK 17 or higher)

### Using Terminal / CLI
```bash
# 1. Compile all Java source files
javac -d bin src/main/java/com/pedro/**/*.java src/main/java/com/pedro/*.java

# 2. Run the application
java -cp bin com.pedro.Main
```

---

## 📄 License
MIT License. Created by Pedro Henrique Valença Ramos.
