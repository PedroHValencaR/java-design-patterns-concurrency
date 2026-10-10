package com.pedro.patterns.dao;

import java.util.Objects;

/**
 * Domain entity representing a university student.
 * Fully encapsulated with validation and immutability for identification.
 */
public class Student {
    private final String id;
    private String name;
    private String email;
    private String major;

    public Student(String id, String name, String email, String major) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Student ID cannot be null or empty.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Student name cannot be null or empty.");
        this.id = id;
        this.name = name;
        this.email = email;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
