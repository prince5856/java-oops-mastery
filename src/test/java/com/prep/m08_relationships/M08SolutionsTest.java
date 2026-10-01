package com.prep.m08_relationships;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m08_relationships.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M08SolutionsTest {

    @Tag("solutions")
    @Test
    void testComposition() {
        var engine = new Ex1_CompositionS.Engine("V8");
        var car = new Ex1_CompositionS.Car(engine);
        assertThat(car.getEngine().getType()).isEqualTo("V8");
    }
}
