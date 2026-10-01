package com.prep.m06_abstraction.exercises;

/**
 * Exercise 1 [I]: Abstract Classes and Template Method
 */
public class Ex1_AbstractClass {

    public abstract static class Shape {
        protected String color;

        public Shape(String color) {
            this.color = color;
        }

        // TODO: Create abstract method calculateArea()
        // TODO: Create concrete method printInfo() that calls calculateArea()

        public abstract double calculateArea();

        public void printInfo() {
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static class Circle extends Shape {
        private double radius;

        public Circle(String color, double radius) {
            super(color);
            this.radius = radius;
        }

        // TODO: Implement calculateArea() - return pi * r^2
        @Override
        public double calculateArea() {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
