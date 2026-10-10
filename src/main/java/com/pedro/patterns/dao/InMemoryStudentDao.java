package com.pedro.patterns.dao;

import com.pedro.exceptions.StudentNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe In-Memory implementation of the StudentDao interface.
 * Uses ConcurrentHashMap to prevent race conditions during concurrent data access.
 */
public class InMemoryStudentDao implements StudentDao {
    private final ConcurrentHashMap<String, Student> database = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        if (student == null) throw new IllegalArgumentException("Student cannot be null.");
        database.put(student.getId(), student);
    }

    @Override
    public Optional<Student> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Student> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(database.values()));
    }

    @Override
    public void update(Student student) throws StudentNotFoundException {
        if (student == null || !database.containsKey(student.getId())) {
            throw new StudentNotFoundException("Cannot update: Student with ID " + (student != null ? student.getId() : "null") + " not found.");
        }
        database.put(student.getId(), student);
    }

    @Override
    public void delete(String id) throws StudentNotFoundException {
        if (id == null || database.remove(id) == null) {
            throw new StudentNotFoundException("Cannot delete: Student with ID " + id + " not found.");
        }
    }
}
