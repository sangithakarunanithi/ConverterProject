package com.example.demo.service;


import ch.qos.logback.core.CoreConstants;
import com.example.demo.dto.KeyValueDTO;
import com.example.demo.model.Nodedata;
import com.example.demo.repo.NodeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NodeDataService
{

    @Autowired
    NodeDataRepository nodeDataRepository;




    public List<Nodedata> getall() {
        return nodeDataRepository.findAll();

    }

    public List<KeyValueDTO> getaggregate() {


        List<HashMap<String,KeyValueDTO>> enumvalue = new ArrayList<HashMap<String,KeyValueDTO>>();
        //  List<HashMap<String,KeyValueDTO>> enumvalue = null;
        HashMap<String,List<String>> map = new HashMap<String,List<String>>();
        List<String> maplist = null;

        List<KeyValueDTO> dbResult = nodeDataRepository.findCommonKeyValuePathCount();

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


    public void getaggregateData() {

        List<KeyValueDTO> findCommonKeyValuePathCount = nodeDataRepository.findCommonKeyValuePathCount();




        countAndSort(findCommonKeyValuePathCount);


    }

    private void countAndSort(List<KeyValueDTO> findCommonKeyValuePathCount) {

        HashMap<String,Integer> slashCountMap = new HashMap<String,Integer>();
        HashMap<String,List<String>> collectValueMap = new HashMap<String,List<String>>();


        for (KeyValueDTO element : findCommonKeyValuePathCount) {
            String keypath = element.getKeypath();
            int count = keypath.length() - keypath.replace("/" , "").length();
            slashCountMap.put(keypath,count);

            String keyname = element.getKeyname();



            if(collectValueMap.containsKey(keyname)){
                collectValueMap.get(keyname).add(element.getKeyvalue());
            }else{
                List<String> list = new ArrayList<>();
                list.add(element.getKeyvalue());
                collectValueMap.put(element.getKeyname(),list);
            }

        }



        Object[] slashList = slashCountMap.entrySet().toArray();

        Arrays.sort(slashList, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o1).getValue().compareTo(((Map.Entry<String, Integer>) o2).getValue());
            }
        });

        for (Object each : slashList) {
            System.out.println(((Map.Entry<String, Integer>) each).getKey() + " : "  + ((Map.Entry<String, Integer>) each).getValue());
        } //display purpose // do proper sort


        for( Map.Entry<String, List<String>> valuemap : collectValueMap.entrySet()){
            System.out.println(valuemap.getKey() + "====" + valuemap.getValue());


        }

// fetch parentid





    }
}


