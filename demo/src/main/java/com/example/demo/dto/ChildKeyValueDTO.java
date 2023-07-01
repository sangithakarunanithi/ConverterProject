package com.example.demo.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ChildKeyValueDTO {

    public String keyname;
    public String keyvalue;
    public String keypath;
    public Long count;
    List<HashMap<String, ChildKeyValueDTO>> enumvalue;



    public ChildKeyValueDTO(){}

    public ChildKeyValueDTO(String keyname, String keyvalue, String keypath, Long count, List<HashMap<String, ChildKeyValueDTO>> enumvalue) {
        this.keyname = keyname;
        this.keyvalue = keyvalue;
        this.keypath = keypath;
        this.count = count;
        this.enumvalue = enumvalue;
    }

    public ChildKeyValueDTO(String keyname, String keyvalue, String keypath) {
        this.keyname = keyname;
        this.keyvalue = keyvalue;
        this.keypath = keypath;
    }

    @Override
    public String toString() {
        return "ChildKeyValueDTO{" +
                "keyname='" + keyname + '\'' +
                ", keyvalue='" + keyvalue + '\'' +
                ", keypath='" + keypath + '\'' +
                ", count=" + count ;
    }
}
