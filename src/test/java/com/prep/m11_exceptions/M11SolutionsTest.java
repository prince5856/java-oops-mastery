package com.prep.m11_exceptions;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m11_exceptions.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M11SolutionsTest {

    @Tag("solutions")
    @Test
    void testExceptionHandling() {
        assertThatThrownBy(() -> Ex1_ExceptionHandlingS.riskyMethod())
            .isInstanceOf(Ex1_ExceptionHandlingS.CustomException.class);

        assertThatThrownBy(() -> Ex1_ExceptionHandlingS.unsafeMethod())
            .isInstanceOf(RuntimeException.class);
    }
}
