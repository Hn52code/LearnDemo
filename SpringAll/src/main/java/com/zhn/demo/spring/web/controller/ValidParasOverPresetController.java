package com.zhn.demo.spring.web.controller;

import com.zhn.demo.spring.web.entity.Member;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/* 测试发现报错行为，实际请求参数超出预设参数 */
@Validated
@RestController
@RequestMapping(value = "/valid/paras/overpreset")
public class ValidParasOverPresetController {

    /**
     * 未添加统一异常处理拦截
     * 参数1：~/?key=value，超出后不做错误处理
     * 参数2：~/xxx...[?key=value]，在未发现有对应的URL时，则报错404
     *
     */
    @GetMapping("/simple")
    public String simple() {
        return "simple";
    }

    /**
     * 参数：id=1001&name=zhn&other=desc，超出后不做错误处理
     */
    @PostMapping("/entity_form")
    public String entityForm(Member member) {
        return "entity-form";
    }

    /**
     * 参数：{"id":10001,"name":"zhn","other":"desc"}，超出后不做错误处理
     */
    @PostMapping("/entity_json")
    public String entityJson(@RequestBody Member member) {
        return "entity-json";
    }

}
