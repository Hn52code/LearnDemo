package com.zhn.demo.webexample.dao;

import com.zhn.demo.webexample.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    int addNewStudent(@Param("stu") Student stu);

    int addStudents(@Param("list") List<Student> Students);

    int delStudent(int stuNo);

    int delStudents(@Param("list") List<Integer> stuNos);

    int modifyStudent(@Param("stu") Student stu);

    Student getStudent(int stuNo);

    List<Student> allStudents();

    boolean isExist(int stuNo);

    boolean isExist2(int stuNo);

    int addAndReturnId(@Param("stu") Student stu);


}
