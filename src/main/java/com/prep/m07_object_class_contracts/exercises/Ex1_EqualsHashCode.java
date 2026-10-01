package com.prep.m07_object_class_contracts.exercises;

/**
 * Exercise 1 [I]: Implement equals() and hashCode()
 */
public class Ex1_EqualsHashCode {

    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // TODO: Implement equals() following the contract
        // TODO: Implement hashCode() consistent with equals()
        // TODO: Implement toString()

        @Override
        public boolean equals(Object obj) {
            throw new UnsupportedOperationException("TODO");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("TODO");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
