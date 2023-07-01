package com.example.demo.controller;

import com.example.demo.dto.KeyValueDTO;
import com.example.demo.model.Converter;
import com.example.demo.model.NodeMapping;
import com.example.demo.repo.NodeMappingRepository;
import com.example.demo.service.ConverterService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConverterController1 {


    @Autowired
    public NodeMappingRepository nodeMappingRepository;

    @GetMapping("/freemaker")
    private String getAggregate1() throws TemplateException {


        try {
           List<NodeMapping> node =  nodeMappingRepository.findByParentIdIsNull();
            List<NodeMapping> childMaps = new ArrayList<NodeMapping>();
            for(NodeMapping nMap : node) {
              childMaps = nodeMappingRepository.getDetails(nMap.getId());
            }

            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(ClassTemplateLoaderDemo13.class.getResource("/templates").toURI()));
            Template template = cfg.getTemplate("sample1.ftl");
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("nodeList", node);
            dataModel.put("childMaps", childMaps);

            Writer writer = new OutputStreamWriter(System.out);
            template.process(dataModel, writer);


        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }




        return "okay";

    }
}
