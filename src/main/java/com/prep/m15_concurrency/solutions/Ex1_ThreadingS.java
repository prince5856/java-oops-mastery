package com.prep.m15_concurrency.solutions;

public class Ex1_ThreadingS {

    public static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        c.increment();
    }
}
