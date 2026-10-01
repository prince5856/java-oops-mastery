package com.prep.m02_classes_objects.solutions;

public class Ex1_ConstructorChainingS {
    private String name;
    private int age;
    private String email;

    public Ex1_ConstructorChainingS() {
        this("Unknown", 0, "no-email@example.com");
    }

    public Ex1_ConstructorChainingS(String name, int age) {
        this(name, age, "no-email@example.com");
    }

    public Ex1_ConstructorChainingS(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
}
