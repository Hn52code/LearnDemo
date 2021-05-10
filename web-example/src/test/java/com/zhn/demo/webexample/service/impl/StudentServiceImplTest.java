package com.zhn.demo.webexample.service.impl;

import com.zhn.demo.webexample.ABaseTest;
import com.zhn.demo.webexample.pojo.Student;
import com.zhn.demo.webexample.service.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class StudentServiceImplTest extends ABaseTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void add() {
        Student student = new Student();
        student.setStuName("zhouhainan");
        student.setAge(24);
        student.setSex(1);
        student.setBirthday(new Date());
        student.setAddress("这是来自湖南");
        studentService.add(student);
    }
}