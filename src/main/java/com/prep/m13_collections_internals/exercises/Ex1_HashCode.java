package com.prep.m13_collections_internals.exercises;

import java.util.*;

/**
 * Exercise 1 [I]: HashMap and hashCode()
 */
public class Ex1_HashCode {

    static class Key {
        private String value;

        public Key(String value) {
            this.value = value;
        }

        // TODO: Implement hashCode() and equals()
        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("TODO");
        }

        @Override
        public boolean equals(Object obj) {
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static void testHashMap() {
        Map<Key, String> map = new HashMap<>();
        map.put(new Key("test"), "value");
    }
}
