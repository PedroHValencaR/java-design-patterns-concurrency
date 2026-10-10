package com.pedro.exceptions;

/**
 * Custom checked exception thrown when a requested Student record
 * does not exist in the persistence layer.
 */
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
