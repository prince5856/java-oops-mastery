package com.prep.m07_object_class_contracts;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m07_object_class_contracts.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M07SolutionsTest {

    @Tag("solutions")
    @Test
    void testEqualsHashCode() {
        var p1 = new Ex1_EqualsHashCodeS.Person("Alice", 30);
        var p2 = new Ex1_EqualsHashCodeS.Person("Alice", 30);
        var p3 = new Ex1_EqualsHashCodeS.Person("Bob", 25);

        assertThat(p1).isEqualTo(p2);
        assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
        assertThat(p1).isNotEqualTo(p3);
        assertThat(p1.toString()).contains("Alice");
    }
}
