package com.example.demo.dto;

public record NameValuePath(String name,String value,String path) {

    @Override
    public String toString() {
        return "NameValuePath =";
    }
}

