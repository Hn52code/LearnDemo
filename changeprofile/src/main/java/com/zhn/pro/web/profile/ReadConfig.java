package com.zhn.pro.web.profile;

import com.zhn.pro.web.pojo.Department;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ReadConfig {

    @Profile("dev")
    @Bean
    public Department devDepartment() {
        Department department = new Department();
        department.setAge("1");
        department.setDesc("dev desc");
        department.setName("dev env");
        department.setNumber("10");
        return department;
    }

    @Profile("prod")
    @Bean
    public Department prodDepartment() {
        Department department = new Department();
        department.setAge("2");
        department.setDesc("prod desc");
        department.setName("prod env");
        department.setNumber("20");
        return department;
    }
}
