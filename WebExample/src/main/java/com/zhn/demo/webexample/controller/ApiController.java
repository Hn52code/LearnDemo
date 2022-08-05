package com.zhn.demo.webexample.controller;

import com.zhn.demo.webexample.common.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api3/v1/")
public class ApiController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, Object> login() {
        System.out.println("login");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", 13457423);

        String token = jwtUtil.getToken(hashMap);
        HashMap<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("express", jwtUtil.getExpressTime());
        return result;
    }

    @GetMapping("/login/{id}")
    public void login2(@PathVariable("id") String id) {
        System.out.println(id);
    }

    @GetMapping("/hello")
    public void hello() {
        System.out.println("hello");
    }

    @GetMapping("/hello2")
    public void hello2() {
        System.out.println("hello2");
    }

}
