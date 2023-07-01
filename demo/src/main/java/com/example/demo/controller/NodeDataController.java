package com.example.demo.controller;


import com.example.demo.service.NodeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeDataController
{

    @Autowired
    NodeDataService nodeDataService;

    @GetMapping("/aggregate")
    private void getAggregateData()
    {
         nodeDataService.getaggregateData();
    }



}
