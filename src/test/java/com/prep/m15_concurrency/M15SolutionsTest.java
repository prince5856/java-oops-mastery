package com.prep.m15_concurrency;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m15_concurrency.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M15SolutionsTest {

    @Tag("solutions")
    @Test
    void testThreading() {
        var counter = new Ex1_ThreadingS.Counter();
        counter.increment();
        counter.increment();
        assertThat(counter.getCount()).isEqualTo(2);
    }
}
