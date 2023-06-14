package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Converter
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