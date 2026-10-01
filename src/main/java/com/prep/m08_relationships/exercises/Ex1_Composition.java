package com.prep.m08_relationships.exercises;

/**
 * Exercise 1 [I]: Composition
 */
public class Ex1_Composition {

    public static class Engine {
        private String type;

        public Engine(String type) {
            this.type = type;
        }

        public String getType() { return type; }
    }

    public static class Car {
        private Engine engine;  // Composition: Car has-a Engine

        // TODO: Implement constructor with Engine
        // TODO: Implement getEngine()

        public Engine getEngine() {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
