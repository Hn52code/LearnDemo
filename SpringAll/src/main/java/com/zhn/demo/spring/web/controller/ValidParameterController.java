package com.zhn.demo.spring.web.controller;

import com.zhn.demo.spring.web.entity.Company;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/* demo 参数验证 */
@Validated  // 告诉MethodValidationPostProcessor此Bean需要开启方法级别验证支持
@RestController
@RequestMapping(value = "/extend/parameter/valid")
public class ValidParameterController {

    /**
     * 值得注意的地方：
     * 1. 参数前需要加上@Validated注解，表明需要spring对其进行校验，而校验的信息会存放到其后的BindingResult中。
     * 注意，必须相邻，如果有多个参数需要校验，
     * 形式可以如下。foo(@Validated Foo foo, BindingResult fooBindingResult ，@Validated Bar bar, BindingResult barBindingResult);
     * 即一个校验类对应一个校验结果。
     * 2.校验结果会被自动填充，在controller中可以根据业务逻辑来决定具体的操作，如跳转到错误页面.
     * JSR和Hibernate validator的校验只能对Object的属性进行校验。
     */

    /* 验证普通参数 url: /extend/parameter/valid/18?name=zhouhainan */
    @GetMapping("/simple/{age}")
    public void validSimpleParameter(
            @Min(value = 18, message = "未成年不可") @PathVariable("age") int age,
            @Length(min = 5, max = 16, message = "长度不符") @RequestParam("name") String name) {
        System.out.println("age: " + age + " name: " + name);
    }

    /* 当被检验的参数后不跟 BindingResult result，则错误将上抛至全局异常，做全局异常统一处理时，此处这处理便好 */
    /* json实体 */
    @PostMapping("/obj/json/validresult")
    public String validEntityParaByRequestBodyAndBindResult(@RequestBody @Valid Company company, BindingResult result) {
        StringBuilder builder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors)
                builder.append(error.getDefaultMessage());
        }
        return builder.toString();
    }

    /* json实体 */
    @PostMapping("/obj/json")
    public String validEntityParaByRequestBodyThrowError(@RequestBody @Valid Company company) {
        return "";
    }

    /* 表单实体 接收Content-Type为application/x-www-form-urlencoded */
    @PostMapping("/obj")
    public String validEntityParaBodyThrowError(@Valid Company company) {
        return "";
    }

}
