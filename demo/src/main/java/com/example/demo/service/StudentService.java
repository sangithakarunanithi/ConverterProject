package com.example.demo.service;


import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService
{
@Autowired
public StudentRepository studentRepository;

    public List<Student> getAllStudent() {
        return studentRepository.findAll();

    }
}