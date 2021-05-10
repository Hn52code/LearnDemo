package com.zhn.demo.spring.web.controller;

import com.zhn.demo.spring.web.entity.Sex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/test/enum")
public class EnumParasController {

    @GetMapping("/get")
    public String getEnum(Sex sex){
        return sex.name();
    }

}
