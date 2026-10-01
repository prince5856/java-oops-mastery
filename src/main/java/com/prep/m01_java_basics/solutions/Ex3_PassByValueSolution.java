package com.prep.m01_java_basics.solutions;

/**
 * Solution for Exercise 3: Pass-by-Value Semantics
 *
 * Java is ALWAYS pass-by-value. For primitives, you pass the value itself (a copy).
 * For references, you pass the reference value (which is still a copy of the reference).
 * Modifying a primitive parameter does NOT affect the caller's primitive.
 * Modifying an object via a reference parameter DOES affect the caller's object.
 * Reassigning a reference parameter does NOT affect the caller's reference.
 *
 * Interviewer might ask:
 * - Is Java pass-by-reference? (No, it's pass-by-value)
 * - What happens if I reassign a parameter? (Only the local parameter changes, not the caller's)
 * - Can I modify a collection passed as a parameter? (Yes, you can modify its contents)
 */
public class Ex3_PassByValueSolution {

    public static class Container {
        public int value;

        public Container(int value) {
            this.value = value;
        }
    }

    public int incrementPrimitive(int x) {
        return ++x;  // Increment and return; caller's x is unchanged
    }

    public int incrementContainer(Container c) {
        c.value++;  // Modify the object via the reference; caller's object is changed
        return c.value;
    }

    public int reassignContainer(Container c) {
        c.value = 99;  // Modify original before reassigning
        c = new Container(100);  // Reassign the local reference; caller's reference unchanged
        return c.value;
    }

    public int swapAndSum(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        return temp + a;  // Return original sum before swap; caller's vars unchanged
    }
}
