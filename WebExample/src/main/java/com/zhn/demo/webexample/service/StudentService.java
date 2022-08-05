package com.zhn.demo.webexample.service;

import com.zhn.demo.webexample.pojo.Student;

public interface StudentService {

    int add(Student student) throws RuntimeException;

    int del(int stuNo);

    int update(Student student);

    Student get(int stuNo);


}
