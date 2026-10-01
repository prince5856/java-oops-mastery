package com.prep.m02_classes_objects.solutions;

public class Ex2_InitializationOrderS {
    static {
        // Static block runs at class load (first)
    }

    private int instanceVar = 10;

    {
        // Instance initialization block runs before constructor
    }

    public Ex2_InitializationOrderS() {
        // Constructor runs last
    }

    public int getInstanceVar() { return instanceVar; }
}
