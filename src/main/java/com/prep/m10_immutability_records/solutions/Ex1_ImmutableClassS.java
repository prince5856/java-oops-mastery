package com.prep.m10_immutability_records.solutions;

import java.util.Objects;

public final class Ex1_ImmutableClassS {
    private final String name;
    private final int age;

    public Ex1_ImmutableClassS(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ex1_ImmutableClassS that = (Ex1_ImmutableClassS) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Immutable{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
