package com.zhn.demo.webexample.service.impl;

import com.zhn.demo.webexample.dao.StudentMapper;
import com.zhn.demo.webexample.pojo.Student;
import com.zhn.demo.webexample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public int add(Student student) throws RuntimeException {

        studentMapper.addNewStudent(student);
//        String str = null;
//        if (str == null)
//            throw new BusinessException(-1, "为空");
        return 0;
    }

    @Override
    public int del(int stuNo) {
        return 0;
    }

    @Override
    public int update(Student student) {
        return 0;
    }

    @Override
    public Student get(int stuNo) {
        return null;
    }


}
