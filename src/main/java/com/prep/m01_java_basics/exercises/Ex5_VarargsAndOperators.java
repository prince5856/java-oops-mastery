package com.prep.m01_java_basics.exercises;

/**
 * Exercise 5 [I]: Varargs and Operators
 *
 * Understand varargs syntax, null handling, and operator behavior.
 *
 * TODO: Implement the methods below.
 */
public class Ex5_VarargsAndOperators {

    /**
     * Sum all integers passed as varargs.
     * Handle null and empty varargs arrays gracefully.
     */
    public int sumAll(int... numbers) {
        throw new UnsupportedOperationException("TODO: implement sumAll");
    }

    /**
     * Concatenate all strings passed as varargs using a separator.
     * Example: concat(", ", "a", "b", "c") -> "a, b, c"
     * Handle null varargs (throw NullPointerException).
     */
    public String concat(String separator, String... parts) {
        throw new UnsupportedOperationException("TODO: implement concat");
    }

    /**
     * Check if an object is an instance of a given class.
     * Use instanceof operator.
     * Return false if obj is null.
     */
    public boolean isInstance(Object obj, Class<?> clazz) {
        throw new UnsupportedOperationException("TODO: implement isInstance");
    }

    /**
     * Use the ternary operator to return "yes" if condition is true, "no" otherwise.
     */
    public String ternaryCheck(boolean condition) {
        throw new UnsupportedOperationException("TODO: implement ternaryCheck");
    }

    /**
     * Perform a bitwise left shift on x by n positions.
     * Example: shiftLeft(1, 3) -> 8 (1 << 3 = 8)
     */
    public int shiftLeft(int x, int n) {
        throw new UnsupportedOperationException("TODO: implement shiftLeft");
    }

    /**
     * Perform a bitwise right shift on x by n positions (sign-extending).
     * Example: shiftRight(8, 3) -> 1 (8 >> 3 = 1)
     */
    public int shiftRight(int x, int n) {
        throw new UnsupportedOperationException("TODO: implement shiftRight");
    }
}
