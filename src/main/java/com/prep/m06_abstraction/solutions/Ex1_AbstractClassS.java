package com.prep.m06_abstraction.solutions;

public class Ex1_AbstractClassS {

    public abstract static class Shape {
        protected String color;

        public Shape(String color) {
            this.color = color;
        }

        public abstract double calculateArea();

        public void printInfo() {
            System.out.println("Color: " + color + ", Area: " + calculateArea());
        }
    }

    public static class Circle extends Shape {
        private double radius;

        public Circle(String color, double radius) {
            super(color);
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }
}
