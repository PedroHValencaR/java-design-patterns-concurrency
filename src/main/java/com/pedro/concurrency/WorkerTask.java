package com.pedro.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * WorkerTask represents an independent computational unit executed concurrently by threads.
 */
public class WorkerTask implements Runnable {
    private final int taskId;
    private final AtomicInteger processedCounter;

    public WorkerTask(int taskId, AtomicInteger processedCounter) {
        this.taskId = taskId;
        this.processedCounter = processedCounter;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.printf("[%s] Processing task #%d...%n", threadName, taskId);
            // Simulate processing time
            Thread.sleep(100 + (long)(Math.random() * 150));
            int currentCompleted = processedCounter.incrementAndGet();
            System.out.printf("[%s] Completed task #%d (Total completed so far: %d)%n", threadName, taskId, currentCompleted);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.printf("[%s] Task #%d was interrupted!%n", threadName, taskId);
        }
    }
}
