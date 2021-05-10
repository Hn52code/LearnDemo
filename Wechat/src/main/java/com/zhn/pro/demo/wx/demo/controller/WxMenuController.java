package com.zhn.pro.demo.wx.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu")
public class WxMenuController {

    @ResponseBody
    @PostMapping("/add")
    public String create() {
        return null;
    }

    @ResponseBody
    @DeleteMapping("/del")
    public String delete() {

        return null;
    }

    @ResponseBody
    @GetMapping("/")
    public String get() {

        return null;
    }

}
