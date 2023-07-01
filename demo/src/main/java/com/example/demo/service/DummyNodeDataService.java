package com.example.demo.service;


import com.example.demo.dto.ChildKeyValueDTO;
import com.example.demo.dto.KeyValueDTO;
import com.example.demo.dto.NameValuePath;
import com.example.demo.repo.NodeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DummyNodeDataService
{

    @Autowired
    NodeDataRepository nodeDataRepository;




//    public List<Nodedata> getall() {
//        return nodeDataRepository.findAll();
//
//    }
//
//    public List<KeyValueDTO> getaggregate() {
//
//
//        List<HashMap<String,KeyValueDTO>> enumvalue = new ArrayList<HashMap<String,KeyValueDTO>>();
//        //  List<HashMap<String,KeyValueDTO>> enumvalue = null;
//        HashMap<String,List<String>> map = new HashMap<String,List<String>>();
//        List<String> maplist = null;
//
//        List<KeyValueDTO> dbResult = nodeDataRepository.findCommonKeyValuePathCount();
//
//        List<KeyValueDTO> result = new ArrayList<KeyValueDTO>();
//        KeyValueDTO newObj = new KeyValueDTO();
//
//        HashMap<String,KeyValueDTO> enumValuemap = new   HashMap<String,KeyValueDTO>();
//
//        for(KeyValueDTO firstagg : dbResult){
//
//
//            if(firstagg.getKeyname().equalsIgnoreCase("componentType")){
//
//
//                if(map.containsKey(firstagg.getKeyname()) ){
//
//                    System.out.println("*******inside map if******");
//
//                    map.get(firstagg.getKeyname()).add(firstagg.getKeyvalue());
//
//                    System.out.println("*******inside map if map******" +map.toString());
//
//
//                    enumValuemap.put(firstagg.getKeyvalue(),new KeyValueDTO());
//
//                    System.out.println("*******inside map if enumValuemap******" +enumValuemap.toString());
//
//                    //KeyValueDTO dto = new KeyValueDTO();
//
//
//
//                    //  enumvalue.add(enumValuemap);
//
//                    //  newObj = new KeyValueDTO(); //check
//                    //   newObj.setKeyname(firstagg.getKeyname());
//                    //   newObj.setEnumvalue(enumvalue);
//
//                    //    result.add(newObj);
//
//                } else{
//
//                    System.out.println("*******inside map else****");
//
//                    List<String> resValue = new ArrayList<String>();
//                    resValue.add(firstagg.getKeyvalue());
//
//                    map.put(firstagg.getKeyname(),resValue);
//
//
//                    System.out.println("*******inside map else map******" +map.toString());
//
//
//                    enumValuemap.put(firstagg.getKeyvalue(),new KeyValueDTO());
//
//
//                    System.out.println("*******inside map else enumValuemap******" +enumValuemap.toString());
//
//
//
//                    //    enumvalue = new ArrayList<HashMap<String,KeyValueDTO>>();
//
//                    //  enumvalue.add(enumValuemap);
//
//                    //  newObj = new KeyValueDTO(); //check
//                    //  newObj.setKeyname(firstagg.getKeyname());
//                    //   newObj.setEnumvalue(enumvalue);
//
//
//                    //      result.add(newObj);
//
//
//                }
//
//                System.out.println("*******outsidee enumValuemap ******" +enumValuemap.toString());
//                enumvalue = new ArrayList<HashMap<String,KeyValueDTO>>();
//
//                enumvalue.add(enumValuemap);
//
//
//                System.out.println("*******outsideenumvalue ******" +enumvalue.toString());
//
//                newObj = new KeyValueDTO(); //check
//                newObj.setKeyname(firstagg.getKeyname());
//                newObj.setEnumvalue(enumvalue);
//
//                System.out.println("*******outsidee newObj ******" +newObj);
//
//
//                result.add(newObj);
//            }//if componenttype
//
//
//
//        } //for
//
//        //List<HashMap<String,KeyValueDTO>> enumvalue
//
//
//
//
//
//
//
//        System.out.println("*************");
//        System.out.println("*************");
//        System.out.println("*************");
//
//        System.out.println("--newObj--" +newObj);
//
//        return dbResult;
//
//
//
//    }


    public void getaggregateData() {

        List<KeyValueDTO> findCommonKeyValuePathCount = nodeDataRepository.findCommonKeyValuePathCount();

        countAndSort(findCommonKeyValuePathCount);


    }

    private void countAndSort(List<KeyValueDTO> findCommonKeyValuePathCount) {

        HashMap<String,Integer> slashCountMap = new HashMap<String,Integer>();
        HashMap<String,List<String>> collectValueMap = new HashMap<String,List<String>>();

        HashMap<String,KeyValueDTO> storevalueMap = new HashMap<String,KeyValueDTO>();


        for (KeyValueDTO element : findCommonKeyValuePathCount) {
            String keypath = element.getKeypath();
            int count = keypath.length() - keypath.replace("/" , "").length();
            slashCountMap.put(keypath,count);

            String keyname = element.getKeyname();


            if(collectValueMap.containsKey(keyname)){
                collectValueMap.get(keyname).add(element.getKeyvalue());

                storevalueMap.put(element.getKeyvalue(),element);

            }else{
                List<String> list = new ArrayList<>();
                list.add(element.getKeyvalue());
                collectValueMap.put(element.getKeyname(),list);

                storevalueMap.put(element.getKeyvalue(),element);
            }

        }



        for( Map.Entry<String, List<String>> valuemap : collectValueMap.entrySet()) {
            System.out.println(valuemap.getKey() + "==***********&&&&&&&&&&&&&&&&&&&&&&==" + valuemap.getValue());
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

            // fetch parentid
           for(String eachValue :   valuemap.getValue()){
               if(storevalueMap.containsKey(eachValue)){
                  System.out.println("eachvalue " + eachValue);
                   KeyValueDTO keydto = storevalueMap.get(eachValue);
                   List<Integer> parentId = nodeDataRepository.fetchByParentID(keydto.getKeypath(), keydto.getKeyvalue(), keydto.getKeyname());
                  parentId.stream().forEach(System.out::println);


                   //step 6 : run query with list of parentid
                   List<ChildKeyValueDTO> childData = nodeDataRepository.findChildDataByParentId(parentId);

                   System.out.println(" ************childData*************************** "  + childData);


//step 7 : identify enum

                   Map<NameValuePath, List<ChildKeyValueDTO>> childenum = childData.stream().filter(p -> p.getCount() > 1)
                           .collect(
                                   Collectors.groupingBy(
                                           p -> new NameValuePath(p.getKeyname(), p.getKeyvalue() ,p.getKeypath()),
                                           Collectors.mapping(p -> p, Collectors.toList())
                                   )
                           );



                   System.out.println(" ************childData**************childenum************* "  + childenum);

                   HashMap<String,List<String>> childcollectValueMap = new HashMap<String,List<String>>();
                   HashMap<String,List<String>> childValueMap = new HashMap<String,List<String>>();



                   for( Map.Entry<NameValuePath, List<ChildKeyValueDTO>> eachChildenum :  childenum.entrySet()) {

                       List<ChildKeyValueDTO> childEnumValueList = eachChildenum.getValue();
                        for(ChildKeyValueDTO eachchild  : childEnumValueList ){

                            if(childValueMap.containsKey(eachchild.getKeypath())){
                                childValueMap.get(eachchild.getKeypath()).add(eachchild.getKeyvalue());
                                childcollectValueMap.get(eachchild.getKeyname()).add(eachchild.getKeyvalue());

                            }else{
                                List<String> list = new ArrayList<>();
                              list.add(eachchild.getKeyvalue());

                                List<String> list1 = new ArrayList<>();
                                list1.add(eachchild.getKeyvalue());
                                childValueMap.put(eachchild.getKeypath(), list);
                                childcollectValueMap.put(eachchild.getKeyname(),list1);
                            }

                        }
                   }
                   for( Map.Entry<String, List<String>> valuemap1 : childcollectValueMap.entrySet()) {
                       if(valuemap1.getValue().size()>1){
                           System.out.println("child ==============" + valuemap1.getKey() + " === " + valuemap1.getValue());
                       }

                   }
////extra
//                   for( Map.Entry<String, List<String>> valuemap1 : childValueMap.entrySet()) {
//                       if(valuemap1.getValue().size()>1){
//                           System.out.println("child ==============" + valuemap1.getKey() + " === " + valuemap1.getValue());
//                       }
//
//                   }




               }
           }

        }









    }
}


