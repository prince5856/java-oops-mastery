package com.prep.m04_inheritance.exercises;

/**
 * Exercise 1 [I]: Method Overriding and super()
 */
public class Ex1_MethodOverriding {

    public static class Animal {
        private String name;

        public Animal(String name) {
            this.name = name;
        }

        public String getName() { return name; }
        public String makeSound() { return "Some sound"; }
    }

    public static class Dog extends Animal {
        private String breed;

        public Dog(String name, String breed) {
            super(name);  // Call parent constructor (required)
            // TODO: set this.breed
            throw new UnsupportedOperationException("TODO");
        }

        public String getBreed() {
            throw new UnsupportedOperationException("TODO");
        }

        public String makeSound() {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
