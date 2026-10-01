package com.prep.m01_java_basics.exercises;

/**
 * Exercise 7 [A]: Advanced Autoboxing and Null Handling
 *
 * Tricky autoboxing scenarios that appear in interviews.
 */
public class Ex7_AdvancedAutoboxing {

    /**
     * Test autoboxing with null. What exceptions can happen?
     */
    public static int testNullUnboxing(Integer x) {
        // TODO: Handle null safely, return -1 if null, else return value
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Compare two boxed integers using .equals() vs ==.
     * Return true only if content is equal (use .equals()).
     */
    public static boolean compareBoxedIntegersCorrectly(Integer a, Integer b) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Fix the bug: this code has a NullPointerException risk.
     * Safely compare Integer values.
     */
    public static boolean isBiggerThanTen(Integer value) {
        // Buggy: if (value > 10) return true;  // NPE if value is null
        // TODO: Fix this
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Autobox multiple times. Return the sum without NPE.
     * Handle null arguments gracefully.
     */
    public static int sumWithNullSafety(Integer... numbers) {
        // TODO: Sum all numbers, skip nulls, return sum
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Create two Integer objects with same value (300).
     * Return true if they are the SAME object (reference equality).
     * Note: 300 is outside cache range.
     */
    public static boolean areSameReference() {
        Integer a = Integer.valueOf(300);
        Integer b = Integer.valueOf(300);
        // TODO: return a == b; // Expected: false
        throw new UnsupportedOperationException("TODO");
    }
}
