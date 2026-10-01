package com.prep.m01_java_basics.solutions;

/**
 * Solution for Exercise 5: Varargs and Operators
 *
 * Varargs (int... args) is syntactic sugar for an array (int[] args).
 * Calling foo(1, 2, 3) is equivalent to foo(new int[]{1, 2, 3}).
 * The array can be null if explicitly passed: foo(null).
 * Varargs must be the last parameter and only one per method.
 *
 * instanceof: returns false if obj is null.
 * Ternary (?:): short-circuits; only one branch is evaluated.
 * Bitwise shifts: <<, >>, >>> (the last is zero-extending for unsigned).
 *
 * Interviewer might ask:
 * - What if I call foo(null)? (null varargs, not empty array)
 * - Can I have multiple varargs? (No, only one, and it must be last)
 * - What's the difference between >> and >>>? (>> sign-extends, >>> zero-extends)
 */
public class Ex5_VarargsAndOperatorsSolution {

    public int sumAll(int... numbers) {
        int sum = 0;
        if (numbers != null) {
            for (int num : numbers) {
                sum += num;
            }
        }
        return sum;
    }

    public String concat(String separator, String... parts) {
        if (parts == null) {
            throw new NullPointerException("parts is null");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) sb.append(separator);
            sb.append(parts[i]);
        }
        return sb.toString();
    }

    public boolean isInstance(Object obj, Class<?> clazz) {
        if (obj == null) return false;
        return obj instanceof Object && clazz.isInstance(obj);  // Simplified: just check if obj is an Object
        // Better: return obj.getClass().equals(clazz) || clazz.isAssignableFrom(obj.getClass());
    }

    public String ternaryCheck(boolean condition) {
        return condition ? "yes" : "no";
    }

    public int shiftLeft(int x, int n) {
        return x << n;
    }

    public int shiftRight(int x, int n) {
        return x >> n;
    }
}
