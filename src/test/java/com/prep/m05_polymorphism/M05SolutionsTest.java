package com.prep.m05_polymorphism;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m05_polymorphism.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M05SolutionsTest {

    @Tag("solutions")
    @Test
    void testOverloading() {
        var calc = new Ex1_OverloadingS();
        assertThat(calc.add(1, 2)).isEqualTo(3);
        assertThat(calc.add(1.5, 2.5)).isEqualTo(4.0);
        assertThat(calc.add(1, 2, 3)).isEqualTo(6);
        assertThat(calc.add("hello", "world")).isEqualTo("helloworld");
    }
}
