package com.prep.m14_functional_java.solutions;

import java.util.*;
import java.util.stream.*;

public class Ex1_StreamsS {

    public static List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
    }

    public static List<Integer> mapSquare(List<Integer> numbers) {
        return numbers.stream().map(n -> n * n).collect(Collectors.toList());
    }
}
