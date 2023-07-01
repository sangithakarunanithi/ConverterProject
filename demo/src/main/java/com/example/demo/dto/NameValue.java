package com.example.demo.dto;

public record NameValue(String name, String value) {

    @Override
    public String toString() {
        return "NameValue=";
    }
}

