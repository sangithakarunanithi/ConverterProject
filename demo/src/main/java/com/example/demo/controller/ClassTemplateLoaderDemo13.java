package com.example.demo.controller;

import com.example.demo.dto.MyPojo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassTemplateLoaderDemo13 {
	public static void main(String[] args) {
        try {


            List<MyPojo> pojoList = new ArrayList<>();
            pojoList.add(new MyPojo("John", 25));
            pojoList.add(new MyPojo("Alice", 30));
            pojoList.add(new MyPojo("Bob", 35));



            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(ClassTemplateLoaderDemo13.class.getResource("/templates").toURI()));
            Template template = cfg.getTemplate("sample1.ftl");


            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("pojoList", pojoList);

            Writer writer = new OutputStreamWriter(System.out);
            template.process(dataModel, writer);


        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
