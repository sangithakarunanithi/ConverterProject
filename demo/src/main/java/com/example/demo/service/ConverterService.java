package com.example.demo.service;


import ch.qos.logback.core.CoreConstants;
import com.example.demo.dto.KeyValueDTO;
import com.example.demo.model.Converter;
import com.example.demo.repo.ConverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConverterService
{
@Autowired
public ConverterRepository converterRepository;


    public List<Converter> getallDetails() {
        return converterRepository.findAll();
    }

    public   List<KeyValueDTO>  getAggregate() {

        List<KeyValueDTO> keyvalue = converterRepository.findAggregate();




        System.out.println("output" + keyvalue);


        return keyvalue;



    }
}