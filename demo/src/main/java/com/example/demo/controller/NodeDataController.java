package com.example.demo.controller;


import com.example.demo.dto.KeyValueDTO;
import com.example.demo.model.Nodedata;
import com.example.demo.service.NodeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NodeDataController
{

    @Autowired
    NodeDataService nodeDataService;

//    @GetMapping("/nodedata/aggregate")
//    private  List<KeyValueDTO> getNodeAggregate()
//    {
//
//        return nodeDataService.getNodeDataAggregate();
//    }


    @GetMapping("/nodedata")
    private List<Nodedata> getallNode()
    {
        return nodeDataService.getall();
    }

    @GetMapping("/nodedata/aggregate")
    private List<KeyValueDTO> getNodeAggregate()
    {
        return nodeDataService.getaggregate();
    }  //old code


    @GetMapping("/aggregate")
    private void getAggregateData()
    {
         nodeDataService.getaggregateData();
    }



}
