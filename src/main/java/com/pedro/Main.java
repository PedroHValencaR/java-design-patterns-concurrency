package com.pedro;

import com.pedro.concurrency.ConcurrentTaskProcessor;
import com.pedro.exceptions.StudentNotFoundException;
import com.pedro.patterns.dao.InMemoryStudentDao;
import com.pedro.patterns.dao.Student;
import com.pedro.patterns.dao.StudentDao;
import com.pedro.patterns.factory.Notification;
import com.pedro.patterns.factory.NotificationFactory;
import com.pedro.patterns.singleton.DatabaseConnectionPool;

/**
 * Main application class showcasing:
 * 1. Data Access Object (DAO) Pattern with CRUD and exception handling
 * 2. Factory Method Pattern for notifications
 * 3. Double-Checked Locking Singleton Pattern
 * 4. Multi-threaded Concurrent Processing
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("  JAVA DESIGN PATTERNS & MULTI-THREADED CONCURRENCY SUITE");
        System.out.println("  Author: Pedro Henrique Valença Ramos");
        System.out.println("=============================================================\n");

        // 1. DATA ACCESS OBJECT (DAO) DEMONSTRATION
        System.out.println("--- 1. Testing DAO Pattern (Student Entity CRUD) ---");
        StudentDao studentDao = new InMemoryStudentDao();

        studentDao.save(new Student("STU-101", "Pedro Ramos", "pedro@university.edu", "Software Engineering"));
        studentDao.save(new Student("STU-102", "Ana Silva", "ana@university.edu", "Computer Science"));
        studentDao.save(new Student("STU-103", "Lucas Lima", "lucas@university.edu", "Mechatronics"));

        System.out.println("Listing all students in repository:");
        studentDao.findAll().forEach(s -> System.out.println("  -> " + s));

        System.out.println("\nQuerying student STU-101:");
        studentDao.findById("STU-101").ifPresent(s -> System.out.println("  Found: " + s.getName() + " (" + s.getMajor() + ")"));

        System.out.println("\nUpdating student STU-102:");
        try {
            Student updated = new Student("STU-102", "Ana Silva Costa", "ana.costa@university.edu", "Software Engineering");
            studentDao.update(updated);
            System.out.println("  Update successful: " + studentDao.findById("STU-102").orElse(null));
        } catch (StudentNotFoundException e) {
            System.err.println("  Error: " + e.getMessage());
        }

        System.out.println("\nTesting custom exception handling (deleting non-existent student):");
        try {
            studentDao.delete("STU-999");
        } catch (StudentNotFoundException e) {
            System.out.println("  Caught expected exception: " + e.getMessage());
        }

        // 2. FACTORY METHOD PATTERN DEMONSTRATION
        System.out.println("\n--- 2. Testing Factory Method Pattern ---");
        Notification email = NotificationFactory.createNotification("EMAIL");
        email.send("pedro@university.edu", "Your assignment grade has been published.");

        Notification sms = NotificationFactory.createNotification("SMS");
        sms.send("+55 22 99880-2011", "Security alert: New login detected.");

        // 3. SINGLETON PATTERN DEMONSTRATION
        System.out.println("\n--- 3. Testing Thread-Safe Singleton Pattern ---");
        DatabaseConnectionPool pool1 = DatabaseConnectionPool.getInstance();
        DatabaseConnectionPool pool2 = DatabaseConnectionPool.getInstance();

        pool1.executeQuery("SELECT * FROM students WHERE status = 'ACTIVE';");
        System.out.println("Are pool1 and pool2 the exact same memory instance? " + (pool1 == pool2));

        // 4. CONCURRENT MULTI-THREADING DEMONSTRATION
        System.out.println("\n--- 4. Testing Multi-Threaded Concurrency (Worker Threads) ---");
        ConcurrentTaskProcessor processor = new ConcurrentTaskProcessor(4);
        processor.processTasks(8);

        System.out.println("\n=============================================================");
        System.out.println("  ALL SUITE TESTS EXECUTED SUCCESSFULLY!");
        System.out.println("=============================================================");
    }
}
