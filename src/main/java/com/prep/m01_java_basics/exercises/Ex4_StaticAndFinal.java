package com.prep.m01_java_basics.exercises;

/**
 * Exercise 4 [I]: Static and Final Keywords
 *
 * Understand static vs instance, and final immutability.
 *
 * TODO: Implement the class below with proper static and final fields.
 */
public class Ex4_StaticAndFinal {

    // TODO: Declare a static counter that tracks how many instances have been created.
    // It should be incremented in the constructor.
    // Write accessor: getInstanceCount()

    // TODO: Declare an immutable (final) id field for each instance (assigned in constructor).
    // Write accessor: getId()

    // TODO: Declare a static final constant MAGIC_NUMBER = 42.
    // Write accessor: getMagicNumber()

    private int id;

    public Ex4_StaticAndFinal(int id) {
        // TODO: increment the instance counter
        throw new UnsupportedOperationException("TODO: implement constructor");
    }

    public int getId() {
        throw new UnsupportedOperationException("TODO: implement getId");
    }

    public static int getInstanceCount() {
        throw new UnsupportedOperationException("TODO: implement getInstanceCount");
    }

    public static int getMagicNumber() {
        throw new UnsupportedOperationException("TODO: implement getMagicNumber");
    }

    public static void resetCounter() {
        // TODO: reset the static counter to 0
        throw new UnsupportedOperationException("TODO: implement resetCounter");
    }
}
