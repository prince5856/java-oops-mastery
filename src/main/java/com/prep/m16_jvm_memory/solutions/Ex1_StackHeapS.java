package com.prep.m16_jvm_memory.solutions;

public class Ex1_StackHeapS {

    public static class Object1 {
        public int x = 10;
    }

    public static void main(String[] args) {
        int primitiveVar = 5;      // Stack
        Object1 obj = new Object1(); // obj reference on stack, object on heap
    }
}
