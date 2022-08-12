package com.zhn.demo.spring.web.controller;

import com.zhn.demo.spring.web.entity.Company;
import com.zhn.demo.spring.web.res.Result;
import com.zhn.demo.spring.web.res.ResultUtil;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.List;

/**
 * 参数验证controller示例</p>
 * 测试发现请求参数超出预设参数，并不会报错，缺少参数时，需配合校验提示才会报错
 * 在使用注解和全局异常捕捉时，请注意注解类和异常类来源包。
 * <p>
 * 值得注意的地方：
 * 1. 参数前需要加上@Validated注解，表明需要spring对其进行校验，而校验的信息会存放到其后的BindingResult中。
 * 注意，必须相邻，如果有多个参数需要校验，即一个校验类对应一个校验结果。
 * 形式可以如下。foo(@Validated Foo foo, BindingResult fooBindingResult ，@Validated Bar bar, BindingResult barBindingResult);
 * 2.校验结果会被自动填充，在controller中可以根据业务逻辑来决定具体的操作，如跳转到错误页面.
 * JSR和Hibernate validator的校验只能对Object的属性进行校验。
 */
@Validated  // 告诉MethodValidationPostProcessor此Bean需要开启方法级别验证支持
@RestController
@RequestMapping(value = "/valid/paras")
public class ValidParasController {

    /**
     * @link 该方式理解上不能算作客户请求400错误，只能被认为是服务端内部错误
     * uri中没有{id}，而方法中携带参数id，则报错MissingPathVariableException类
     */
    @Deprecated
    @GetMapping("/simple")
    public Result validSimpleParameter(@PathVariable Integer id) {
        System.out.println("id: " + id);
        return ResultUtil.createSucResult(null);
    }

    /**
     * 验证普通参数 url: /valid/paras/simple/{id}?name=xxx
     * 该接口参数不符合要求的检验错误，交由ConstraintViolationException类处理
     */
    @GetMapping("/simple/{id}")
    public Result validSimple2Parameter(
            @Min(value = 0, message = "编号不可低于0") @PathVariable("id") int id,
            @Length(min = 5, max = 16, message = "长度不符") @RequestParam("name") String name) {
        System.out.println("id: " + id + " name: " + name);
        return ResultUtil.createSucResult(null);
    }

    /**
     * 使用@Valid和@Validated效果
     * 表单实体 接收Content-Type为application/x-www-form-urlencoded
     * 表单参数，校验结果交由BindException类处理
     */
    @PostMapping(value = "/obj", consumes = "application/x-www-form-urlencoded")
    public Result validForm1ThrowError(@Validated Company company) {
        System.out.println(company.toString());
        return ResultUtil.createSucResult(null);
    }

    /**
     * 使用@Validated和@Valid同样效果
     * json实体，校验结果交由MethodArgumentNotValidException类处理；
     */
    @PostMapping("/obj/json")
    public Result validRequestBodyThrowError(@Validated @RequestBody Company company) {
        System.out.println(company.toString());
        return ResultUtil.createSucResult(null);
    }

    /**
     * json实体，校验对象（company）后跟随 BindingResult 实例参数时，
     * 校验结果产生的错误将交由BindingResult类实例接收；
     * 在使用BindingResult时，需注意该类需要配合@Validated才能生效；
     */
    @PostMapping("/obj/json/validresult")
    public Result validRequestBodyAndBindResult(@Validated @RequestBody Company company, BindingResult result) {
        System.out.println(company.toString());
        StringBuilder builder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors)
                builder.append(error.getDefaultMessage());
        }
        return ResultUtil.createSucResult(builder.toString());
    }

    /**
     * 该方式不可取
     * 使用@Valid注解json实体后跟随BindingResult实例时，实例result不会生效，
     * 且校验结果交由ConstraintViolationException类处理，而非MethodArgumentNotValidException类
     */
    @Deprecated
    @PostMapping("/obj/json/validresult2")
    public Result validRequestBodyAndBindResult2(@Valid @RequestBody Company company, BindingResult result) {
        System.out.println(company.toString());
        StringBuilder builder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors)
                builder.append(error.getDefaultMessage());
        }
        return ResultUtil.createSucResult(builder.toString());
    }

    /**
     * 单上传文件
     * 上传文件时，请求类型content-type默认为multipart/form-data，
     * 文件请求参数“file”必须和MultipartFile前的@RequestParam("file")一致
     */
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public Result validUploadThrowError(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        System.out.println(new String(file.getBytes()));
        return ResultUtil.createSucResult(null);
    }

    /* 上传文件和表单 */
    @PostMapping(value = "/upload2")
    public Result validUpload2ThrowError(@RequestParam("file") MultipartFile file,
                                         @Validated Company company) throws IOException {
        System.out.println(file.getOriginalFilename()
                + " " + file.getName()
                + " " + file.getSize()
                + " " + file.getContentType());
        System.out.println(new String(file.getBytes()));
        System.out.println(company.toString());
        return ResultUtil.createSucResult(null);
    }

}
