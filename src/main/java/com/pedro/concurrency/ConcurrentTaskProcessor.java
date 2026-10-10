package com.pedro.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages an asynchronous worker thread pool using Java ExecutorService.
 * Demonstrates thread synchronization, safe concurrency, and graceful shutdown.
 */
public class ConcurrentTaskProcessor {
    private final int threadPoolSize;
    private final AtomicInteger processedCounter = new AtomicInteger(0);

    public ConcurrentTaskProcessor(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public void processTasks(int totalTasks) {
        System.out.printf("[CONCURRENCY] Initializing thread pool with %d worker threads for %d tasks.%n", threadPoolSize, totalTasks);
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        for (int i = 1; i <= totalTasks; i++) {
            executor.execute(new WorkerTask(i, processedCounter));
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("[CONCURRENCY] Tasks timed out, forcing shutdown.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.printf("[CONCURRENCY] All tasks finished. Total processed atomically: %d%n", processedCounter.get());
    }

    public int getProcessedCount() {
        return processedCounter.get();
    }
}
