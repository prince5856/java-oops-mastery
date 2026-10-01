package com.prep.m16_jvm_memory;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m16_jvm_memory.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M16SolutionsTest {

    @Tag("solutions")
    @Test
    void testStackHeap() {
        var obj = new Ex1_StackHeapS.Object1();
        assertThat(obj.x).isEqualTo(10);
    }
}
