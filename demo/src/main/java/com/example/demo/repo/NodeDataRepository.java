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

    @Query("select parentId from Nodedata  where path = ?1 and value = ?2  and name = ?3")
    List<Integer> fetchByParentID(String path, String value, String name);

//    @Query(value="SELECT name as keyname,  value as keyvalue,  path as keypath , count(*) as count"  +
//            "FROM Nodedata JOIN ( WITH RECURSIVE cte AS ( " +
//            "SELECT id, parentId FROM nodedata WHERE id IN ( ?1 ) " +
//            "UNION all " +
//            "SELECT t.id, t.parentId FROM Nodedata t INNER JOIN count(*) ON parentId = cte.id ) " +
//            "SELECT id FROM cte WHERE id NOT IN ( ?1 ) ) AS cte ON db.nodedata.id = cte.id " +
//            "where value is not null group by name, path,value having cnt > 1 " +
//            "ORDER BY name", nativeQuery = true)
//    List<KeyValueDTO> findAllChild(List<Integer> parentId);
//
//@Query(value="SELECT db.nodedata.name as keyname ,db.nodedata.path as keypath ,db.nodedata.value as keyvalue ,count(*) as count\n" +
//        "FROM db.nodedata\n" +
//        "JOIN (\n" +
//        "    WITH RECURSIVE cte AS (\n" +
//        "        SELECT id, parent_id\n" +
//        "        FROM db.nodedata\n" +
//        "        WHERE id IN (:parentId) " +
//        "       \n" +
//        "        UNION ALL\n" +
//        "       \n" +
//        "        SELECT t.id, t.parent_id\n" +
//        "        FROM db.nodedata t\n" +
//        "        INNER JOIN cte ON t.parent_id = cte.id\n" +
//        "    )\n" +
//        "    SELECT id\n" +
//        "    FROM cte\n" +
//        "    WHERE id NOT IN ( :parentId)" +
//        ") AS cte ON db.nodedata.id = cte.id\n" +
//        "where db.nodedata.value is not null\n" +
//        "group by db.nodedata.name, db.nodedata.path,db.nodedata.value\n" +
//        "having count > 1\n" +
//        "ORDER BY db.nodedata.name", nativeQuery = true)
//List<String> findAllChild(List<Integer> parentId);


    @Query(nativeQuery = true)
    List<KeyValueDTO> findChildDataByParentId(List<Integer> parentId);


}
