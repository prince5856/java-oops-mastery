package com.prep.m09_nested_classes.solutions;

public class Ex1_NestedClassesS {

    public static class Logger {
        public void log(String message) {
            System.out.println(message);
        }
    }

    public class Counter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int getValue() {
            return count;
        }
    }
}
