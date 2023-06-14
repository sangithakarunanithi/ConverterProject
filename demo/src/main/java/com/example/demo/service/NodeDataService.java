package com.example.demo.service;


import ch.qos.logback.core.CoreConstants;
import com.example.demo.dto.KeyValueDTO;
import com.example.demo.model.Nodedata;
import com.example.demo.repo.NodeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NodeDataService
{

    @Autowired
    NodeDataRepository nodeDataRepository;


//    public List<KeyValueDTO> getNodeDataAggregate() {
//        List<KeyValueDTO> ans = nodeDataRepository.findAggregate();
//
//        System.out.println("ans " +ans);
//
//        return ans;
//    }

    public List<Nodedata> getall() {
        return nodeDataRepository.findAll();

    }

    public List<KeyValueDTO> getaggregate() {


        List<HashMap<String,KeyValueDTO>> enumvalue = new ArrayList<HashMap<String,KeyValueDTO>>();
        //  List<HashMap<String,KeyValueDTO>> enumvalue = null;
        HashMap<String,List<String>> map = new HashMap<String,List<String>>();
        List<String> maplist = null;

        List<KeyValueDTO> dbResult = nodeDataRepository.findAggregate();

        List<KeyValueDTO> result = new ArrayList<KeyValueDTO>();
        KeyValueDTO newObj = new KeyValueDTO();

        HashMap<String,KeyValueDTO> enumValuemap = new   HashMap<String,KeyValueDTO>();

        for(KeyValueDTO firstagg : dbResult){


            if(firstagg.getKeyname().equalsIgnoreCase("componentType")){


                if(map.containsKey(firstagg.getKeyname()) ){

                    System.out.println("*******inside map if******");

                    map.get(firstagg.getKeyname()).add(firstagg.getKeyvalue());

                    System.out.println("*******inside map if map******" +map.toString());


                    enumValuemap.put(firstagg.getKeyvalue(),new KeyValueDTO());

                    System.out.println("*******inside map if enumValuemap******" +enumValuemap.toString());

                    //KeyValueDTO dto = new KeyValueDTO();



                    //  enumvalue.add(enumValuemap);

                    //  newObj = new KeyValueDTO(); //check
                    //   newObj.setKeyname(firstagg.getKeyname());
                    //   newObj.setEnumvalue(enumvalue);

                    //    result.add(newObj);

                } else{

                    System.out.println("*******inside map else****");

                    List<String> resValue = new ArrayList<String>();
                    resValue.add(firstagg.getKeyvalue());

                    map.put(firstagg.getKeyname(),resValue);


                    System.out.println("*******inside map else map******" +map.toString());


                    enumValuemap.put(firstagg.getKeyvalue(),new KeyValueDTO());


                    System.out.println("*******inside map else enumValuemap******" +enumValuemap.toString());



                    //    enumvalue = new ArrayList<HashMap<String,KeyValueDTO>>();

                    //  enumvalue.add(enumValuemap);

                    //  newObj = new KeyValueDTO(); //check
                    //  newObj.setKeyname(firstagg.getKeyname());
                    //   newObj.setEnumvalue(enumvalue);


                    //      result.add(newObj);


                }

                System.out.println("*******outsidee enumValuemap ******" +enumValuemap.toString());
                enumvalue = new ArrayList<HashMap<String,KeyValueDTO>>();

                enumvalue.add(enumValuemap);


                System.out.println("*******outsideenumvalue ******" +enumvalue.toString());

                newObj = new KeyValueDTO(); //check
                newObj.setKeyname(firstagg.getKeyname());
                newObj.setEnumvalue(enumvalue);

                System.out.println("*******outsidee newObj ******" +newObj);


                result.add(newObj);
            }//if componenttype



        } //for

        //List<HashMap<String,KeyValueDTO>> enumvalue







        System.out.println("*************");
        System.out.println("*************");
        System.out.println("*************");

        System.out.println("--newObj--" +newObj);

        return dbResult;



    }



}


