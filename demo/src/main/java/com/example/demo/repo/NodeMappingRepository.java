package com.example.demo.repo;

import com.example.demo.dto.ChildKeyValueDTO;
import com.example.demo.dto.KeyValueDTO;
import com.example.demo.model.NodeMapping;
import com.example.demo.model.Nodedata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NodeMappingRepository extends JpaRepository<NodeMapping, Integer>
{
   List<NodeMapping> findByParentIdIsNull();

   @Query(nativeQuery = true,
           value = "SELECT *\r\n"
                   + "FROM db.node_mapping\r\n"
                   + "WHERE id IN(\r\n"
                   + "WITH RECURSIVE cte AS (\r\n"
                   + "    SELECT id, parent_id\r\n"
                   + "    FROM db.node_mapping\r\n"
                   + "    WHERE id in (?1)\r\n"
                   + "   \r\n"
                   + "    UNION ALL\r\n"
                   + "   \r\n"
                   + "    SELECT t.id, t.parent_id\r\n"
                   + "    FROM db.node_mapping t\r\n"
                   + "    INNER JOIN cte ON t.parent_id = cte.id\r\n"
                   + ")\r\n"
                   + "SELECT id\r\n"
                   + "FROM cte\r\n"
                   + "WHERE id not in (?1)\r\n"
                   + ")")
   List<NodeMapping> getDetails(@Param("id") Integer id);




}
