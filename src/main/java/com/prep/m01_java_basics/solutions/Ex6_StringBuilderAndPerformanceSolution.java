package com.prep.m01_java_basics.solutions;

/**
 * Solution for Exercise 6: StringBuilder and Performance
 *
 * String concatenation with + in a loop creates a new String object each iteration,
 * because strings are immutable. Use StringBuilder to build the result incrementally.
 *
 * StringBuilder is mutable and not thread-safe (use StringBuffer if you need thread-safety, rare).
 * StringBuilder.toString() is the final method that creates the resulting String.
 *
 * Interviewer might ask:
 * - Why not use String concatenation in a loop? (Performance: each + creates a new object)
 * - What's the difference between StringBuilder and StringBuffer? (StringBuffer is synchronized; rarely needed)
 * - How does StringBuilder work internally? (Internal char[] array that grows as needed)
 */
public class Ex6_StringBuilderAndPerformanceSolution {

    public String buildString(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(",");
            sb.append(i);
        }
        return sb.toString();
    }

    public String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    public String join(String[] parts, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) sb.append(delimiter);
            sb.append(parts[i]);
        }
        return sb.toString();
    }
}
