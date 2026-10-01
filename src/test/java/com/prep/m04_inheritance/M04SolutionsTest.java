package com.prep.m04_inheritance;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m04_inheritance.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M04SolutionsTest {

    @Tag("solutions")
    @Test
    void testMethodOverriding() {
        var dog = new Ex1_MethodOverridingS.Dog("Buddy", "Labrador");
        assertThat(dog.getName()).isEqualTo("Buddy");
        assertThat(dog.getBreed()).isEqualTo("Labrador");
        assertThat(dog.makeSound()).isEqualTo("Woof");

        Ex1_MethodOverridingS.Animal animal = new Ex1_MethodOverridingS.Dog("Max", "Golden");
        assertThat(animal.makeSound()).isEqualTo("Woof");  // Dynamic dispatch
    }
}
