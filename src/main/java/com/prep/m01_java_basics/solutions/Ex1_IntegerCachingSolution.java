package com.prep.m01_java_basics.solutions;

/**
 * Solution for Exercise 1: Integer Caching and Autoboxing
 *
 * The Integer cache caches values from -128 to 127. When you use Integer.valueOf() or autoboxing
 * within this range, you get the same cached object back. Outside this range, you get different objects.
 *
 * Interviewer might ask:
 * - Why is the cache in this range? (Common use case optimization)
 * - What's the performance implication? (Reduced object creation)
 * - How does this affect equals() vs ==? (== checks identity, not equality)
 */
public class Ex1_IntegerCachingSolution {

    public boolean areIdentical(Integer a, Integer b) {
        return a == b;  // Reference equality using ==
    }

    public boolean compareAutoboxedIntegers(int a, int b) {
        Integer boxedA = a;  // Autobox to Integer.valueOf(a)
        Integer boxedB = b;  // Autobox to Integer.valueOf(b)
        return boxedA == boxedB;  // Reference equality; true only if both are cached
    }

    public int safeUnbox(Integer value) {
        return value != null ? value : -1;  // Safe unboxing with null check
    }
}
