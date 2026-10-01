package com.prep.m09_nested_classes;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m09_nested_classes.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M09SolutionsTest {

    @Tag("solutions")
    @Test
    void testNestedClasses() {
        var logger = new Ex1_NestedClassesS.Logger();
        logger.log("test");  // Should not throw

        var outer = new Ex1_NestedClassesS();
        var counter = outer.new Counter();
        counter.increment();
        assertThat(counter.getValue()).isEqualTo(1);
    }
}
