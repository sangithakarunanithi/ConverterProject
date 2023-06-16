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



    public KeyValueDTO(){}

    public KeyValueDTO(String keyname, String keyvalue, String keypath, Long count, List<HashMap<String, KeyValueDTO>> enumvalue) {
        this.keyname = keyname;
        this.keyvalue = keyvalue;
        this.keypath = keypath;
        this.count = count;
        this.enumvalue = enumvalue;
    }


    @Override
    public String toString() {
        return
                "{keyname='" + keyname + '\'' +
                ", keyvalue='" + keyvalue + '\'' +
                ", keypath='" + keypath + '\'' +
                ", count=" + count +
                ", enumvalue=" + enumvalue +
                '}';
    }
}
