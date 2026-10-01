package com.prep.m13_collections_internals.solutions;

import java.util.*;

public class Ex1_HashCodeS {

    public static class Key {
        private String value;

        public Key(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Key key = (Key) obj;
            return Objects.equals(value, key.value);
        }
    }

    public static void testHashMap() {
        Map<Key, String> map = new HashMap<>();
        map.put(new Key("test"), "value");
    }
}
