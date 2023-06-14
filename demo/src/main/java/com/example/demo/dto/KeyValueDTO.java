package com.example.demo.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class KeyValueDTO {

    public String keyname;
    public String keyvalue;
    public String keypath;
    public Long count;
    List<HashMap<String,KeyValueDTO>> enumvalue;

    @Override
    public String toString() {
        return keypath  ;


    }
}
