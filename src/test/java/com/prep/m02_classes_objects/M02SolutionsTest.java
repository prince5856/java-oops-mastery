package com.prep.m02_classes_objects;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m02_classes_objects.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M02SolutionsTest {

    @Tag("solutions")
    @Test
    void testConstructorChaining() {
        var p1 = new Ex1_ConstructorChainingS();
        assertThat(p1.getName()).isEqualTo("Unknown");
        assertThat(p1.getAge()).isEqualTo(0);

        var p2 = new Ex1_ConstructorChainingS("Alice", 25);
        assertThat(p2.getName()).isEqualTo("Alice");
        assertThat(p2.getAge()).isEqualTo(25);
        assertThat(p2.getEmail()).isEqualTo("no-email@example.com");

        var p3 = new Ex1_ConstructorChainingS("Bob", 30, "bob@example.com");
        assertThat(p3.getEmail()).isEqualTo("bob@example.com");
    }

    @Tag("solutions")
    @Test
    void testInitializationOrder() {
        var obj = new Ex2_InitializationOrderS();
        assertThat(obj.getInstanceVar()).isEqualTo(10);
    }
}
