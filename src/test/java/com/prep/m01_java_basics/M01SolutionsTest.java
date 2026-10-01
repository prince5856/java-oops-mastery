package com.prep.m01_java_basics;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.prep.m01_java_basics.solutions.*;

import static org.assertj.core.api.Assertions.*;

public class M01SolutionsTest {

    // ===== Solution 1 Tests =====

    @Tag("solutions")
    @Test
    void testSol1_IntegerCaching() {
        var sol = new Ex1_IntegerCachingSolution();

        Integer a = Integer.valueOf(100);
        Integer b = Integer.valueOf(100);
        assertThat(sol.areIdentical(a, b)).isTrue();

        Integer c = Integer.valueOf(200);
        Integer d = Integer.valueOf(200);
        assertThat(sol.areIdentical(c, d)).isFalse();
    }

    @Tag("solutions")
    @Test
    void testSol1_CompareAutoboxedIntegers() {
        var sol = new Ex1_IntegerCachingSolution();
        assertThat(sol.compareAutoboxedIntegers(50, 50)).isTrue();
        assertThat(sol.compareAutoboxedIntegers(200, 200)).isFalse();
    }

    @Tag("solutions")
    @Test
    void testSol1_SafeUnbox() {
        var sol = new Ex1_IntegerCachingSolution();
        assertThat(sol.safeUnbox(42)).isEqualTo(42);
        assertThat(sol.safeUnbox(null)).isEqualTo(-1);
    }

    // ===== Solution 2 Tests =====

    @Tag("solutions")
    @Test
    void testSol2_StringPoolIdenticalReferences() {
        var sol = new Ex2_StringPoolAndImmutabilitySolution();

        String a = "hello";
        String b = "hello";
        assertThat(sol.areIdenticalReferences(a, b)).isTrue();

        String c = new String("hello");
        String d = new String("hello");
        assertThat(sol.areIdenticalReferences(c, d)).isFalse();
    }

    @Tag("solutions")
    @Test
    void testSol2_StringPoolEqualContent() {
        var sol = new Ex2_StringPoolAndImmutabilitySolution();

        String a = "hello";
        String b = new String("hello");
        assertThat(sol.areEqualContent(a, b)).isTrue();
    }

    @Tag("solutions")
    @Test
    void testSol2_Concatenate() {
        var sol = new Ex2_StringPoolAndImmutabilitySolution();
        assertThat(sol.concatenate("hello", "world")).isEqualTo("helloworld");
    }

    @Tag("solutions")
    @Test
    void testSol2_Intern() {
        var sol = new Ex2_StringPoolAndImmutabilitySolution();
        String a = new String("test");
        String b = sol.internString(a);
        String c = "test";
        assertThat(sol.areIdenticalReferences(b, c)).isTrue();
    }

    // ===== Solution 3 Tests =====

    @Tag("solutions")
    @Test
    void testSol3_IncrementPrimitive() {
        var sol = new Ex3_PassByValueSolution();
        int x = 5;
        int result = sol.incrementPrimitive(x);
        assertThat(result).isEqualTo(6);
        assertThat(x).isEqualTo(5);
    }

    @Tag("solutions")
    @Test
    void testSol3_IncrementContainer() {
        var sol = new Ex3_PassByValueSolution();
        var c = new Ex3_PassByValueSolution.Container(5);
        int result = sol.incrementContainer(c);
        assertThat(result).isEqualTo(6);
        assertThat(c.value).isEqualTo(6);
    }

    @Tag("solutions")
    @Test
    void testSol3_ReassignContainer() {
        var sol = new Ex3_PassByValueSolution();
        var c = new Ex3_PassByValueSolution.Container(5);
        int result = sol.reassignContainer(c);
        assertThat(result).isEqualTo(100);
        assertThat(c.value).isEqualTo(99);  // Original was set to 99
    }

    // ===== Solution 4 Tests =====

    @BeforeEach
    void resetSol4Counter() {
        Ex4_StaticAndFinalSolution.resetCounter();
    }

    @Tag("solutions")
    @Test
    void testSol4_StaticAndFinal() {
        assertThat(Ex4_StaticAndFinalSolution.getInstanceCount()).isEqualTo(0);

        var obj1 = new Ex4_StaticAndFinalSolution(1);
        assertThat(obj1.getId()).isEqualTo(1);
        assertThat(Ex4_StaticAndFinalSolution.getInstanceCount()).isEqualTo(1);

        var obj2 = new Ex4_StaticAndFinalSolution(2);
        assertThat(obj2.getId()).isEqualTo(2);
        assertThat(Ex4_StaticAndFinalSolution.getInstanceCount()).isEqualTo(2);

        assertThat(Ex4_StaticAndFinalSolution.getMagicNumber()).isEqualTo(42);
    }

    // ===== Solution 5 Tests =====

    @Tag("solutions")
    @Test
    void testSol5_SumAll() {
        var sol = new Ex5_VarargsAndOperatorsSolution();
        assertThat(sol.sumAll(1, 2, 3)).isEqualTo(6);
        assertThat(sol.sumAll()).isEqualTo(0);
    }

    @Tag("solutions")
    @Test
    void testSol5_Concat() {
        var sol = new Ex5_VarargsAndOperatorsSolution();
        assertThat(sol.concat(", ", "a", "b", "c")).isEqualTo("a, b, c");
    }

    @Tag("solutions")
    @Test
    void testSol5_IsInstance() {
        var sol = new Ex5_VarargsAndOperatorsSolution();
        assertThat(sol.isInstance("hello", String.class)).isTrue();
        assertThat(sol.isInstance(null, String.class)).isFalse();
    }

    @Tag("solutions")
    @Test
    void testSol5_Ternary() {
        var sol = new Ex5_VarargsAndOperatorsSolution();
        assertThat(sol.ternaryCheck(true)).isEqualTo("yes");
        assertThat(sol.ternaryCheck(false)).isEqualTo("no");
    }

    @Tag("solutions")
    @Test
    void testSol5_Shifts() {
        var sol = new Ex5_VarargsAndOperatorsSolution();
        assertThat(sol.shiftLeft(1, 3)).isEqualTo(8);
        assertThat(sol.shiftRight(8, 3)).isEqualTo(1);
    }

    // ===== Solution 6 Tests =====

    @Tag("solutions")
    @Test
    void testSol6_BuildString() {
        var sol = new Ex6_StringBuilderAndPerformanceSolution();
        assertThat(sol.buildString(3)).isEqualTo("0,1,2");
    }

    @Tag("solutions")
    @Test
    void testSol6_ReverseString() {
        var sol = new Ex6_StringBuilderAndPerformanceSolution();
        assertThat(sol.reverseString("hello")).isEqualTo("olleh");
    }

    @Tag("solutions")
    @Test
    void testSol6_Repeat() {
        var sol = new Ex6_StringBuilderAndPerformanceSolution();
        assertThat(sol.repeat("ab", 3)).isEqualTo("ababab");
    }

    @Tag("solutions")
    @Test
    void testSol6_Join() {
        var sol = new Ex6_StringBuilderAndPerformanceSolution();
        assertThat(sol.join(new String[]{"a", "b", "c"}, "-")).isEqualTo("a-b-c");
    }
}
