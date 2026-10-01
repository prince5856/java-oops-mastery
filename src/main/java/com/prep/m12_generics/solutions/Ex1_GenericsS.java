package com.prep.m12_generics.solutions;

public class Ex1_GenericsS {

    public static <T> T getFirst(T[] array) {
        return array.length > 0 ? array[0] : null;
    }

    public static <T extends Number> double sumNumbers(T[] numbers) {
        double sum = 0;
        for (T num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }
}
