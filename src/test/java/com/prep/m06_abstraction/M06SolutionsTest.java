package com.prep.m06_abstraction;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m06_abstraction.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M06SolutionsTest {

    @Tag("solutions")
    @Test
    void testAbstractClass() {
        var circle = new Ex1_AbstractClassS.Circle("Red", 5.0);
        assertThat(circle.calculateArea()).isCloseTo(Math.PI * 25, within(0.01));
    }
}
