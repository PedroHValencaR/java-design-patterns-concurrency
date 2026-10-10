package com.pedro.patterns.singleton;

/**
 * Thread-safe Singleton implementation using Double-Checked Locking.
 * Models a database connection pool manager ensuring only one instance exists across all threads.
 */
public class DatabaseConnectionPool {
    private static volatile DatabaseConnectionPool instance;
    private final String connectionUrl;
    private final int maxPoolSize;

    private DatabaseConnectionPool() {
        this.connectionUrl = "jdbc:postgresql://localhost:5432/university_db";
        this.maxPoolSize = 10;
        System.out.println("[SINGLETON] Initializing DatabaseConnectionPool with max pool size: " + maxPoolSize);
    }

    public static DatabaseConnectionPool getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionPool.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionPool();
                }
            }
        }
        return instance;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void executeQuery(String sql) {
        System.out.println("[SQL EXECUTION via Pool Instance: " + System.identityHashCode(this) + "] " + sql);
    }
}
