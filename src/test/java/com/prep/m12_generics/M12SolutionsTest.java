package com.prep.m12_generics;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m12_generics.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M12SolutionsTest {

    @Tag("solutions")
    @Test
    void testGenerics() {
        String[] strings = {"a", "b", "c"};
        assertThat(Ex1_GenericsS.getFirst(strings)).isEqualTo("a");

        Double[] numbers = {1.0, 2.0, 3.0};
        assertThat(Ex1_GenericsS.sumNumbers(numbers)).isCloseTo(6.0, within(0.01));
    }
}
