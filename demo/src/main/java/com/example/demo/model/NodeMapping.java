package com.example.demo.model;


import com.example.demo.dto.ChildKeyValueDTO;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="node_mapping")
@Data
public class NodeMapping
{

@Id
@Column(name = "id")
private int id;

    @Column(name = "name")
private String name;

    @Column(name = "xpath")
    private String xpath;

    @Column(name = "canonical_mapping")
    private String canonicalMapping;

    @Column(name = "source_node_id")
    private String sourceNodeId;

    @Column(name = "target_node_id")
    private String target_node_id;


    @Column(name = "parent_id")
    private String parentId;






}