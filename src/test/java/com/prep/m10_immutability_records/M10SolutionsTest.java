package com.prep.m10_immutability_records;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m10_immutability_records.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M10SolutionsTest {

    @Tag("solutions")
    @Test
    void testImmutableClass() {
        var obj = new Ex1_ImmutableClassS("Alice", 30);
        assertThat(obj.getName()).isEqualTo("Alice");
        assertThat(obj.getAge()).isEqualTo(30);
    }
}
