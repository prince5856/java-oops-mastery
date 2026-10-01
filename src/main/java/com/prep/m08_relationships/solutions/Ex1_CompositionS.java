package com.prep.m08_relationships.solutions;

public class Ex1_CompositionS {

    public static class Engine {
        private String type;

        public Engine(String type) {
            this.type = type;
        }

        public String getType() { return type; }
    }

    public static class Car {
        private Engine engine;

        public Car(Engine engine) {
            this.engine = engine;
        }

        public Engine getEngine() {
            return engine;
        }
    }
}
