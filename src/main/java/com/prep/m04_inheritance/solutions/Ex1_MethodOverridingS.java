package com.prep.m04_inheritance.solutions;

public class Ex1_MethodOverridingS {

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
            super(name);
            this.breed = breed;
        }

        public String getBreed() {
            return breed;
        }

        @Override
        public String makeSound() {
            return "Woof";
        }
    }
}
