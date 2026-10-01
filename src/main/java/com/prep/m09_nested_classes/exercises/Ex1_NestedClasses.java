package com.prep.m09_nested_classes.exercises;

/**
 * Exercise 1 [I]: Static and Inner Nested Classes
 */
public class Ex1_NestedClasses {

    // TODO: Create a static nested class Logger with a log(String) method

    public static class Logger {
        public void log(String message) {
            throw new UnsupportedOperationException("TODO");
        }
    }

    // TODO: Create an inner class Counter with increment() and getValue()

    public class Counter {
        private int count = 0;

        public void increment() {
            throw new UnsupportedOperationException("TODO");
        }

        public int getValue() {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
