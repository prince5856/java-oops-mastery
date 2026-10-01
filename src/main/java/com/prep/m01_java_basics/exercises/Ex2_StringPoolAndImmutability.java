package com.prep.m01_java_basics.exercises;

/**
 * Exercise 2 [B]: String Pool and Immutability
 *
 * Understand string pooling and the difference between String literals and new String().
 *
 * TODO: Implement the methods below.
 */
public class Ex2_StringPoolAndImmutability {

    /**
     * Return true if both strings are the same object (reference equality).
     * Use == for reference comparison.
     */
    public boolean areIdenticalReferences(String a, String b) {
        throw new UnsupportedOperationException("TODO: implement areIdenticalReferences");
    }

    /**
     * Given two strings, return true if they are equal in content (value equality).
     * Use equals().
     */
    public boolean areEqualContent(String a, String b) {
        throw new UnsupportedOperationException("TODO: implement areEqualContent");
    }

    /**
     * Create a new string by concatenating a and b.
     * Return the result (may or may not be interned).
     */
    public String concatenate(String a, String b) {
        throw new UnsupportedOperationException("TODO: implement concatenate");
    }

    /**
     * Given a string, intern it (move to the string pool) and return the reference.
     * If the string is already in the pool, return the existing reference.
     */
    public String internString(String str) {
        throw new UnsupportedOperationException("TODO: implement internString");
    }
}
