package com.example.demo.repo;

import com.example.demo.dto.KeyValueDTO;

import com.example.demo.model.Nodedata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NodeDataRepository extends JpaRepository<Nodedata, Integer>
{

//    SELECT name,  value ,  path  , count(*)
//    FROM Nodedata
//    WHERE value  <> 'NULL'
//    group by name, value, path
//    having count(*)  > 1



//    select distinct parent_id  from nodedata
//    where name='componentType'
//    and path= '/teamworks/bpd/BusinessProcessDiagram/pool/lane/flowObject/@componentType'
//    and value='Activity';


    @Query("SELECT new  com.example.demo.dto.KeyValueDTO(name as keyname,  value as keyvalue,  path as keypath , count(*) as count)" +
            " FROM Nodedata where value <> 'NULL' group by name, value, path having count(*)  > 1")
    List<KeyValueDTO> findCommonKeyValuePathCount();

    @Query("SELECT name FROM Nodedata")
   List<String> findAgg();

//    @Query("select parent_id from Nodedata  where path = ?1 and value = ?2  and name = ?3")
//    List<Integer> fetchByParentID(String path, String value, String name);




}
