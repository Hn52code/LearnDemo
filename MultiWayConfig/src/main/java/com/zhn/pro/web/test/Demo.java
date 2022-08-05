package com.zhn.pro.web.test;

import com.zhn.pro.web.pojo.Department;
import com.zhn.pro.web.profile.ReadConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(ReadConfig.class);
        context.refresh();
        Department department = context.getBean(Department.class);
        System.out.println(department);
    }

}
