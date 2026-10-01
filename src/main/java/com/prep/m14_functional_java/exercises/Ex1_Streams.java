package com.prep.m14_functional_java.exercises;

import java.util.*;
import java.util.stream.*;

/**
 * Exercise 1 [I]: Streams and Lambdas
 */
public class Ex1_Streams {

    public static List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
    }

    public static List<Integer> mapSquare(List<Integer> numbers) {
        return numbers.stream().map(n -> n * n).collect(Collectors.toList());
    }
}
