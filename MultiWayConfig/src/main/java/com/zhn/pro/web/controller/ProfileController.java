package com.zhn.pro.web.controller;

import com.zhn.pro.web.entity.DepartmentEntity;
import com.zhn.pro.web.pojo.Department;
import com.zhn.pro.web.pojo.Employ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private Department department;
    @Autowired
    private DepartmentEntity departmentEntity;
    @Autowired
    private Employ employ;

    @GetMapping("/profile")
    public String get() {
        return department.toString();
    }
    @GetMapping("/profile2")
    public String get2(){
        return departmentEntity.toString();
    }
    @GetMapping("/profile3")
    public String get3(){
        return employ.toString();
    }

}
