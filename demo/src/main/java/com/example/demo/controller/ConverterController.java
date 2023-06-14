package com.example.demo.controller;

import com.example.demo.dto.KeyValueDTO;
import com.example.demo.model.Converter;
import com.example.demo.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConverterController
{

@Autowired
public ConverterService converterService;

@GetMapping("/convert")
private List<Converter> getAllStudent()
{
    return converterService.getallDetails();
}

    @GetMapping("/convert/aggregate")
    private  List<KeyValueDTO> getAggregate()
    {
        return converterService.getAggregate();
    }


}
