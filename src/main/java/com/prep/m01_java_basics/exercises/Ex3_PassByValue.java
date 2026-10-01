package com.prep.m01_java_basics.exercises;

/**
 * Exercise 3 [B]: Pass-by-Value Semantics
 *
 * Java is strictly pass-by-value. Understand what this means for primitives and references.
 *
 * TODO: Implement the methods below.
 */
public class Ex3_PassByValue {

    public static class Container {
        public int value;

        public Container(int value) {
            this.value = value;
        }
    }

    /**
     * Increment the primitive by 1 inside this method.
     * Does this affect the caller's variable? No, because primitives are pass-by-value.
     * Return the new value.
     */
    public int incrementPrimitive(int x) {
        throw new UnsupportedOperationException("TODO: implement incrementPrimitive");
    }

    /**
     * Increment the value inside the Container object.
     * This DOES affect the caller's object, because we're modifying the object via the reference.
     * Return the new value.
     */
    public int incrementContainer(Container c) {
        throw new UnsupportedOperationException("TODO: implement incrementContainer");
    }

    /**
     * Reassign the Container reference to a new object inside this method.
     * Does this affect the caller's reference? No, because the reference itself is pass-by-value.
     * Modify the original container's value to 99 before returning the new Container's value.
     */
    public int reassignContainer(Container c) {
        throw new UnsupportedOperationException("TODO: implement reassignContainer");
    }

    /**
     * Swap two integers by value (not references).
     * Does swapping inside the method affect the caller's variables? No.
     * Set both parameters to 0 and return the original sum.
     */
    public int swapAndSum(int a, int b) {
        throw new UnsupportedOperationException("TODO: implement swapAndSum");
    }
}
