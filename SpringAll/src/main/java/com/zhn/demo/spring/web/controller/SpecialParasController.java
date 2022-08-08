package com.zhn.demo.spring.web.controller;

import com.zhn.demo.spring.web.entity.Sex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/special/rest")
public class SpecialParasController {

    @GetMapping("/enum")
    public String getEnum(Sex sex) {
        return sex.name();
    }

    @GetMapping("/time")
    public String getTime(Date date) {
        return date.toString();
    }
}
