package com.prep.m01_java_basics;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.prep.m01_java_basics.exercises.*;
import com.prep.m01_java_basics.solutions.*;

import static org.assertj.core.api.Assertions.*;

public class M01ExercisesTest {

    // ===== Exercise 1 Tests =====

    @Tag("exercises")
    @Test
    void testEx1_IntegerCaching() {
        Ex1_IntegerCaching ex = new Ex1_IntegerCaching();

        // Cached values (-128 to 127)
        Integer a = Integer.valueOf(100);
        Integer b = Integer.valueOf(100);
        assertThat(ex.areIdentical(a, b)).isTrue();

        // Values outside cache
        Integer c = Integer.valueOf(200);
        Integer d = Integer.valueOf(200);
        assertThat(ex.areIdentical(c, d)).isFalse();
    }

    @Tag("exercises")
    @Test
    void testEx1_CompareAutoboxedIntegers() {
        Ex1_IntegerCaching ex = new Ex1_IntegerCaching();
        assertThat(ex.compareAutoboxedIntegers(50, 50)).isTrue();
        assertThat(ex.compareAutoboxedIntegers(200, 200)).isFalse();
    }

    @Tag("exercises")
    @Test
    void testEx1_SafeUnbox() {
        Ex1_IntegerCaching ex = new Ex1_IntegerCaching();
        assertThat(ex.safeUnbox(42)).isEqualTo(42);
        assertThat(ex.safeUnbox(null)).isEqualTo(-1);
    }

    // ===== Exercise 2 Tests =====

    @Tag("exercises")
    @Test
    void testEx2_StringPoolIdenticalReferences() {
        Ex2_StringPoolAndImmutability ex = new Ex2_StringPoolAndImmutability();

        String a = "hello";
        String b = "hello";
        assertThat(ex.areIdenticalReferences(a, b)).isTrue();

        String c = new String("hello");
        String d = new String("hello");
        assertThat(ex.areIdenticalReferences(c, d)).isFalse();
    }

    @Tag("exercises")
    @Test
    void testEx2_StringPoolEqualContent() {
        Ex2_StringPoolAndImmutability ex = new Ex2_StringPoolAndImmutability();

        String a = "hello";
        String b = new String("hello");
        assertThat(ex.areEqualContent(a, b)).isTrue();
    }

    @Tag("exercises")
    @Test
    void testEx2_Concatenate() {
        Ex2_StringPoolAndImmutability ex = new Ex2_StringPoolAndImmutability();
        assertThat(ex.concatenate("hello", "world")).isEqualTo("helloworld");
    }

    @Tag("exercises")
    @Test
    void testEx2_Intern() {
        Ex2_StringPoolAndImmutability ex = new Ex2_StringPoolAndImmutability();
        String a = new String("test");
        String b = ex.internString(a);
        String c = "test";
        assertThat(ex.areIdenticalReferences(b, c)).isTrue();
    }

    // ===== Exercise 3 Tests =====

    @Tag("exercises")
    @Test
    void testEx3_IncrementPrimitive() {
        Ex3_PassByValue ex = new Ex3_PassByValue();
        int x = 5;
        int result = ex.incrementPrimitive(x);
        assertThat(result).isEqualTo(6);
        assertThat(x).isEqualTo(5);  // x is unchanged
    }

    @Tag("exercises")
    @Test
    void testEx3_IncrementContainer() {
        Ex3_PassByValue ex = new Ex3_PassByValue();
        var c = new Ex3_PassByValue.Container(5);
        int result = ex.incrementContainer(c);
        assertThat(result).isEqualTo(6);
        assertThat(c.value).isEqualTo(6);  // Container is modified
    }

    @Tag("exercises")
    @Test
    void testEx3_ReassignContainer() {
        Ex3_PassByValue ex = new Ex3_PassByValue();
        var c = new Ex3_PassByValue.Container(5);
        int result = ex.reassignContainer(c);
        assertThat(result).isEqualTo(100);
        assertThat(c.value).isEqualTo(5);  // c is unchanged; original was set to 99
    }

    // ===== Exercise 4 Tests =====

    @Tag("exercises")
    @Test
    void testEx4_StaticAndFinal() {
        assertThat(Ex4_StaticAndFinal.getInstanceCount()).isEqualTo(0);

        var obj1 = new Ex4_StaticAndFinal(1);
        assertThat(obj1.getId()).isEqualTo(1);
        assertThat(Ex4_StaticAndFinal.getInstanceCount()).isEqualTo(1);

        var obj2 = new Ex4_StaticAndFinal(2);
        assertThat(obj2.getId()).isEqualTo(2);
        assertThat(Ex4_StaticAndFinal.getInstanceCount()).isEqualTo(2);

        assertThat(Ex4_StaticAndFinal.getMagicNumber()).isEqualTo(42);
    }

    // ===== Exercise 5 Tests =====

    @Tag("exercises")
    @Test
    void testEx5_SumAll() {
        Ex5_VarargsAndOperators ex = new Ex5_VarargsAndOperators();
        assertThat(ex.sumAll(1, 2, 3)).isEqualTo(6);
        assertThat(ex.sumAll()).isEqualTo(0);
    }

    @Tag("exercises")
    @Test
    void testEx5_Concat() {
        Ex5_VarargsAndOperators ex = new Ex5_VarargsAndOperators();
        assertThat(ex.concat(", ", "a", "b", "c")).isEqualTo("a, b, c");
        assertThat(ex.concat("-", "x", "y")).isEqualTo("x-y");
    }

    @Tag("exercises")
    @Test
    void testEx5_IsInstance() {
        Ex5_VarargsAndOperators ex = new Ex5_VarargsAndOperators();
        assertThat(ex.isInstance("hello", String.class)).isTrue();
        assertThat(ex.isInstance(42, Integer.class)).isTrue();
        assertThat(ex.isInstance(null, String.class)).isFalse();
    }

    @Tag("exercises")
    @Test
    void testEx5_Ternary() {
        Ex5_VarargsAndOperators ex = new Ex5_VarargsAndOperators();
        assertThat(ex.ternaryCheck(true)).isEqualTo("yes");
        assertThat(ex.ternaryCheck(false)).isEqualTo("no");
    }

    @Tag("exercises")
    @Test
    void testEx5_Shifts() {
        Ex5_VarargsAndOperators ex = new Ex5_VarargsAndOperators();
        assertThat(ex.shiftLeft(1, 3)).isEqualTo(8);
        assertThat(ex.shiftRight(8, 3)).isEqualTo(1);
    }

    // ===== Exercise 6 Tests =====

    @Tag("exercises")
    @Test
    void testEx6_BuildString() {
        Ex6_StringBuilderAndPerformance ex = new Ex6_StringBuilderAndPerformance();
        assertThat(ex.buildString(3)).isEqualTo("0,1,2");
        assertThat(ex.buildString(1)).isEqualTo("0");
    }

    @Tag("exercises")
    @Test
    void testEx6_ReverseString() {
        Ex6_StringBuilderAndPerformance ex = new Ex6_StringBuilderAndPerformance();
        assertThat(ex.reverseString("hello")).isEqualTo("olleh");
    }

    @Tag("exercises")
    @Test
    void testEx6_Repeat() {
        Ex6_StringBuilderAndPerformance ex = new Ex6_StringBuilderAndPerformance();
        assertThat(ex.repeat("ab", 3)).isEqualTo("ababab");
    }

    @Tag("exercises")
    @Test
    void testEx6_Join() {
        Ex6_StringBuilderAndPerformance ex = new Ex6_StringBuilderAndPerformance();
        assertThat(ex.join(new String[]{"a", "b", "c"}, "-")).isEqualTo("a-b-c");
    }
}
