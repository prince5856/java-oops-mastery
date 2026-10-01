package com.prep.m13_collections_internals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m13_collections_internals.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M13SolutionsTest {

    @Tag("solutions")
    @Test
    void testHashCode() {
        var key1 = new Ex1_HashCodeS.Key("test");
        var key2 = new Ex1_HashCodeS.Key("test");
        assertThat(key1.hashCode()).isEqualTo(key2.hashCode());
        assertThat(key1).isEqualTo(key2);
    }
}
