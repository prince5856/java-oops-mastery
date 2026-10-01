package com.prep.m16_jvm_memory.exercises;

/**
 * Exercise 1 [B]: Understanding Stack and Heap
 */
public class Ex1_StackHeap {

    static class Object1 {
        int x = 10;
    }

    public static void main(String[] args) {
        int primitiveVar = 5;      // Stack
        Object1 obj = new Object1(); // obj reference on stack, object on heap
    }
}
