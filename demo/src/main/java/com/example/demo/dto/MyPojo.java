package com.example.demo.dto;

public class MyPojo {
    private String name;
    private int age;

    public MyPojo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}