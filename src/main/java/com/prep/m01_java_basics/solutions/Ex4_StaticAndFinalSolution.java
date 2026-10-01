package com.prep.m01_java_basics.solutions;

/**
 * Solution for Exercise 4: Static and Final Keywords
 *
 * static: class-level, shared by all instances, initialized once at class load.
 * final on field: immutable after initialization (can be set in constructor or static/instance initializer).
 * final on method: cannot override in subclass.
 * final on class: cannot extend.
 *
 * Interviewer might ask:
 * - Can you reassign a final field after construction? (No, it's immutable)
 * - Is a final reference deeply immutable? (No, the reference is immutable, but the object is not)
 * - When is a static field initialized? (At class load time, once)
 */
public class Ex4_StaticAndFinalSolution {

    // Static counter tracking total instances created
    private static int instanceCount = 0;

    // Immutable id field for each instance
    private final int id;

    // Static final constant
    private static final int MAGIC_NUMBER = 42;

    public Ex4_StaticAndFinalSolution(int id) {
        this.id = id;
        instanceCount++;  // Increment static counter
    }

    public int getId() {
        return id;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public static int getMagicNumber() {
        return MAGIC_NUMBER;
    }

    public static void resetCounter() {
        instanceCount = 0;  // Reset static counter
    }
}
