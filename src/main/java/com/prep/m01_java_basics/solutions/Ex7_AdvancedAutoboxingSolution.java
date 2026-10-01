package com.prep.m01_java_basics.solutions;

/**
 * Solution for Exercise 7: Advanced Autoboxing
 */
public class Ex7_AdvancedAutoboxingSolution {

    public static int testNullUnboxing(Integer x) {
        return x == null ? -1 : x;
    }

    public static boolean compareBoxedIntegersCorrectly(Integer a, Integer b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    public static boolean isBiggerThanTen(Integer value) {
        return value != null && value > 10;
    }

    public static int sumWithNullSafety(Integer... numbers) {
        int sum = 0;
        if (numbers != null) {
            for (Integer num : numbers) {
                if (num != null) sum += num;
            }
        }
        return sum;
    }

    public static boolean areSameReference() {
        Integer a = Integer.valueOf(300);
        Integer b = Integer.valueOf(300);
        return a == b;  // false; outside cache range
    }
}
