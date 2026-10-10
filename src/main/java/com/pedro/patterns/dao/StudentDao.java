package com.pedro.patterns.dao;

import com.pedro.exceptions.StudentNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object (DAO) Interface.
 * Defines standard CRUD operations for the Student entity.
 */
public interface StudentDao {
    void save(Student student);
    Optional<Student> findById(String id);
    List<Student> findAll();
    void update(Student student) throws StudentNotFoundException;
    void delete(String id) throws StudentNotFoundException;
}
