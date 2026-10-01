package com.prep.m01_java_basics.solutions;

/**
 * Solution for Exercise 2: String Pool and Immutability
 *
 * String literals are pooled in the heap (since Java 7). Strings created with 'new String()'
 * are NOT automatically pooled. Use intern() to move a string to the pool.
 *
 * Interviewer might ask:
 * - Where is the string pool? (Heap, since Java 7; was PermGen before)
 * - What's the performance implication? (Memory savings for repeated literals)
 * - Can intern() cause memory leaks? (Yes, if you intern arbitrary strings, the pool grows unbounded)
 */
public class Ex2_StringPoolAndImmutabilitySolution {

    public boolean areIdenticalReferences(String a, String b) {
        return a == b;  // Reference equality
    }

    public boolean areEqualContent(String a, String b) {
        return a.equals(b);  // Content equality
    }

    public String concatenate(String a, String b) {
        return a + b;  // Concatenation; result depends on compile-time vs runtime
    }

    public String internString(String str) {
        return str.intern();  // Move to string pool; return the interned reference
    }
}
