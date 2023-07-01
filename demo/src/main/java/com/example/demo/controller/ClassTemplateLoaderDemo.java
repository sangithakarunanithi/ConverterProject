package com.example.demo.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ClassTemplateLoaderDemo {
	public static void main(String[] args) {
        try {
            Configuration cfg = ConfigurationUtil.getConfiguration();
            //Create Data Model 
            Map<String, Object> map = new HashMap<>();
            map.put("pmName", "NaMo");
            map.put("country", "India");
            Template template = cfg.getTemplate("C:/Users/Admin/IdeaProjects/ConverterProject/demo/src/main/resources/SampleFiles/template.ftl");
            //Console output for template
            Writer console = new OutputStreamWriter(System.out);
            template.process(map, console);
            console.flush();
            //File output
            Writer file = new FileWriter (new File("template-output.html"));
            template.process(map, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
 	}
}
