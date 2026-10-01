package com.prep.m02_classes_objects.exercises;

/**
 * Exercise 1 [B]: Constructor Chaining with this()
 *
 * Create a Person class with multiple constructors that chain using this().
 */
public class Ex1_ConstructorChaining {

    private String name;
    private int age;
    private String email;

    // TODO: Implement a no-arg constructor that calls this("Unknown", 0, "no-email@example.com")

    // TODO: Implement a 2-arg constructor (name, age) that calls this(name, age, "no-email@example.com")

    // TODO: Implement a 3-arg constructor (name, age, email) with all fields set

    public String getName() { throw new UnsupportedOperationException("TODO"); }
    public int getAge() { throw new UnsupportedOperationException("TODO"); }
    public String getEmail() { throw new UnsupportedOperationException("TODO"); }
}
