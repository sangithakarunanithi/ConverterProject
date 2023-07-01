package com.example.demo.controller;

import com.example.demo.dto.KeyValueDTO;
import com.example.demo.dto.MyPojo;
import com.example.demo.model.Converter;
import com.example.demo.model.NodeMapping;
import com.example.demo.repo.NodeMappingRepository;
import com.example.demo.service.ConverterService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConverterController {

    @Autowired
    public ConverterService converterService;

    @Autowired
    public NodeMappingRepository nodeMappingRepository;

    @GetMapping("/convert")
    private List<Converter> getAllStudent() {
        return converterService.getallDetails();
    }

    @GetMapping("/convert/aggregate")
    private List<KeyValueDTO> getAggregate() {
        return converterService.getAggregate();
    }


}
