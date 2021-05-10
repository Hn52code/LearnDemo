package com.zhn.demo.webexample.dao;

import com.zhn.demo.webexample.ABaseTest;
import com.zhn.demo.webexample.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class StudentMapperTest extends ABaseTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void addNewStudent() {
        Student ZTestStudent = new Student();
        ZTestStudent.setStuName("zhouhainan");
        ZTestStudent.setAge(24);
        ZTestStudent.setSex(1);
        ZTestStudent.setBirthday(new Date());
        ZTestStudent.setAddress("这是来自湖南");
        studentMapper.addNewStudent(ZTestStudent);
    }

    @Test
    public void addStudents() {
        Student student = new Student();
        student.setStuName("zhouhainan");
        student.setAge(24);
        student.setSex(1);
        student.setBirthday(new Date());
        student.setAddress("这是来自湖南");
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student);
        list.add(student);
        list.add(student);
        list.add(student);
        list.add(student);
        list.add(student);
        list.add(student);
        list.add(student);
        list.add(student);
        list.add(student);
        studentMapper.addStudents(list);
    }

    @Test
    public void delStudent() {
        studentMapper.delStudent(2);
    }

    @Test
    public void delStudents() {
        List<Integer> list = new ArrayList<>();
        list.add(33);
        list.add(34);
        studentMapper.delStudents(list);
    }

    @Test
    public void modifyStudent() {
        Student ZTestStudent = new Student();
        ZTestStudent.setStuNo(36);
        ZTestStudent.setStuName("xiaozhou");
        ZTestStudent.setAge(23);
        ZTestStudent.setSex(2);
        ZTestStudent.setBirthday(new Date());
        ZTestStudent.setAddress("这是深圳的");
        studentMapper.modifyStudent(ZTestStudent);
    }

    @Test
    public void getStudent() {
        Student ZTestStudent = studentMapper.getStudent(57);
        System.out.println(ZTestStudent.getLoginTime2().getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(ZTestStudent.getCreateTime2()));
        System.out.println(format.format(ZTestStudent.getLoginTime2()));
        System.out.println(ZTestStudent.getCreateTime2().toInstant());
        System.out.println(ZTestStudent.getLoginTime2().toInstant());
    }

    @Test
    public void allStudents() {
        List<Student> list = studentMapper.allStudents();
        System.out.println(list);
    }

    @Test
    public void testIsExist() {
        System.out.println(studentMapper.isExist(57));
//        System.out.println(studentMapper.isExist(100));
        System.out.println(studentMapper.isExist2(57));
        System.out.println(studentMapper.isExist2(100));
    }

    @Test
    public void testAddAndReturnId() {
        Student student = new Student();
        student.setStuName("zhouhainan");
        student.setAge(24);
        student.setSex(1);
        student.setBirthday(new Date());
        student.setAddress("这是来自湖南");
        studentMapper.addAndReturnId(student);
        System.out.println(student.getStuNo());
    }
}