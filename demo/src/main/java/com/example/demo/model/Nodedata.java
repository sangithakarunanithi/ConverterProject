package com.example.demo.model;


import com.example.demo.dto.ChildKeyValueDTO;
import com.example.demo.dto.KeyValueDTO;
import jakarta.persistence.*;
import lombok.Data;




@NamedNativeQuery(name = "Nodedata.findChildDataByParentId",
        query = "SELECT nodedata.name as keyname ,nodedata.path as keypath ,nodedata.value as keyvalue ,count(*) as count"  +
                "        FROM db.nodedata" +
                "        JOIN (" +
                "            WITH RECURSIVE cte AS (" +
                "                SELECT id, parent_id" +
                "                FROM db.nodedata" +
                "                WHERE id IN (:parentId) " +
                "                UNION ALL" +
                "               " +
                "                SELECT t.id, t.parent_id" +
                "                FROM db.nodedata t" +
                "                INNER JOIN cte ON t.parent_id = cte.id" +
                "            )" +
                "            SELECT id" +
                "            FROM cte" +
                "            WHERE id NOT IN ( :parentId)       ) AS cte ON db.nodedata.id = cte.id" +
                "        where db.nodedata.value is not null" +
                "        group by db.nodedata.name, db.nodedata.path,db.nodedata.value" +
                "        having count > 1" +
                "        ORDER BY db.nodedata.name",
        resultSetMapping = "Mapping.ChildDataByParentId")
@SqlResultSetMapping(name = "Mapping.ChildDataByParentId",
        classes = @ConstructorResult(targetClass = ChildKeyValueDTO.class,
                columns = {@ColumnResult(name = "keyname"),
                        @ColumnResult(name = "keypath"),
                        @ColumnResult(name = "keyvalue"),
                        @ColumnResult(name = "count")}))

@Entity
@Table(name="nodedata")
@Data
public class Nodedata
{

@Id
@Column(name = "id")
private int id;

    @Column(name = "name")
private String name;

    @Column(name = "top_level_node")
    private String topLevelNode;

    @Column(name = "node_type")
    private String nodeType;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "path")
    private String path;

    @Column(name = "value")
    private String value;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "processing_steps")
    private String processingSteps;

    @Column(name = "platform")
    private String platform;

    @Column(name = "status")
    private String status;

    @Column(name = "properties_id")
    private String propertiesId;

    @Column(name = "parent_id")
    private String parentId;






}