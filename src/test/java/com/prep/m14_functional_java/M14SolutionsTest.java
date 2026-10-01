package com.prep.m14_functional_java;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m14_functional_java.solutions.*;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

public class M14SolutionsTest {

    @Tag("solutions")
    @Test
    void testStreams() {
        var result = Ex1_StreamsS.filterEvenNumbers(List.of(1, 2, 3, 4, 5));
        assertThat(result).containsExactly(2, 4);

        var squared = Ex1_StreamsS.mapSquare(List.of(1, 2, 3));
        assertThat(squared).containsExactly(1, 4, 9);
    }
}
