package com.prep.m01_java_basics.exercises;

/**
 * Exercise 1 [B]: Integer Caching and Autoboxing
 *
 * Understand the Integer cache (-128 to 127) and when == returns true for boxed integers.
 *
 * TODO: Implement the methods below. Do NOT use new Integer(); use Integer.valueOf() or autoboxing.
 *
 * Constraints:
 * - Use only Integer.valueOf() or autoboxing (implicit).
 * - Do not use new Integer() or Integer.parseInt().
 */
public class Ex1_IntegerCaching {

    /**
     * Returns true if the two integers are cached (within -128 to 127).
     * Use == to check reference equality.
     */
    public boolean areIdentical(Integer a, Integer b) {
        throw new UnsupportedOperationException("TODO: implement areIdentical");
    }

    /**
     * Autobox two integers and compare them with ==.
     * Return true if a and b have the same reference (are identical objects).
     * Note: this only works for cached values (-128 to 127).
     */
    public boolean compareAutoboxedIntegers(int a, int b) {
        throw new UnsupportedOperationException("TODO: implement compareAutoboxedIntegers");
    }

    /**
     * Given an Integer, safely unbox it and return the int value.
     * Return -1 if the Integer is null.
     */
    public int safeUnbox(Integer value) {
        throw new UnsupportedOperationException("TODO: implement safeUnbox");
    }
}
