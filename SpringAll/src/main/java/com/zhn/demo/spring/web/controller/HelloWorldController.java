package com.zhn.demo.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @ResponseBody
    @GetMapping("/hello/{name}")
    public String helloWorld(@PathVariable("name") String name) {
        return "say hello to " + name;
    }

}
