package com.zhn.demo.spring.web.controller;

import com.zhn.demo.spring.web.entity.Member;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * SpringMvc全局异常注解实现：
 * 1..@ExceptionHandler 单独使用，在一个控制器中 申明异常处理方法;
 * 2.@ControllerAdvice 配合@ExceptionHandler使用 配置全局异常处理; 在方法上可配置使用@ResponseBody 则返回json
 * 3.@RestControllerAdvice = @ControllerAdvice + @ResponseBody 全局异常rest 返回
 * SpringMvc 对外接口类注解:
 * 1.@Controller 标记当前类为action类，向外暴露接口
 * 2.@ResponseBody 可以标注类上，方法上，标注后接口返回json
 * 3.@RestController 标注类上，用于rest风格的对外接口，相当于 @Controller + @ResponseBody同时标注controller类上
 * 4.@RequestMapping 可以标注类和方法上，用于映射url，同时可以指定请求的其他参数如：contentType，header，method等等。
 * 5.@GetMapping，@PostMapping，@PutMapping，@DeleteMapping 等rest风格的访问接口，作用于方法上。
 * 6.@PathVariable，@RequestParam，@RequestBody以及缺省 此方式作用于参数上，目的在于获取，映射，解析转换位指定的参数。
 * 7.可以在controller中向外暴露的方法加入HttpServletRequest等对象。
 */

@RestController
@RequestMapping("/base/rest")
public class RestBasicUseController {

    /* javaWeb工程：另有资料 https://blog.csdn.net/ahou2468/article/details/79015251 */

    /*@GetMapping()相当于 @RequestMapping(value = "/get",method = RequestMethod.GET) 其他请求方式相同*/
    /*@PathVariable 获取url中的参数，如下面的/get/{id}中的{id}*/
    /*@RequestParam 获取url中“?”后的参数，如下面的/get/123?name=zhn ,它与servlet中getParameter()方法类似*/
    @GetMapping("/get/{id}")
    public String getWithAnnotation(@PathVariable("id") int id,
                                    @RequestParam("name") String name,
                                    @RequestParam("age") int age) {
        System.out.println("id: " + id + " name: " + name + " age: " + age);
        return "getWithAnnotation";
    }

    /* 缺省注解，url:/base/rest/get?id=123&name=zhn&age=24 多个参数... */
    @GetMapping("/get")
    public String getNoAnnotation(int id, String name, int age) {
        System.out.println("id: " + id + " name: " + name + " age: " + age);
        return "getNoAnnotation";
    }

    /* 缺省注解，url:/base/rest/getObj?id=123&name=zhn&age=24 多个参数转对象... */
    @GetMapping("/getObj")
    public String getNoAnnotationObj(Member member) {
        System.out.println(member.toString());
        return "getNoAnnotationObj";
    }

    /**
     * POST请求说明：
     * 1.post请求，表单方式（application/x-www-form-urlencoded;charset=UTF-8）传参，
     * 在springmvc中可通过注解（@RequestParam()）或者缺省 一一映射参数列表，也可以转化为实体类。
     * 2.post请求，json方式（application/json）传参则需要@RequestBody方式转字符串转为响应类对象
     * 可参考servlet解析参数底层：@RequestParam只识别url中参数，和表单式参数。
     */
    /* 注解 参数列表 表单传参，使用注解将表单参数一一对应 */
    @PostMapping("/post")
    public String postWithAnnotation(@RequestParam("id") int id,
                                     @RequestParam("name") String name,
                                     @RequestParam("age") int age) {
        System.out.println("id: " + id + " name: " + name + " age: " + age);
        return "postWithAnnotation";
    }

    /* 无注解 参数列表 表单传参，将表单参数一一列出，无需注解自动映射 */
    @PostMapping("/post2")
    public String postNoAnnotation(int id, String name, int age) {
        System.out.println("id: " + id + " name: " + name + " age: " + age);
        return "postNoAnnotation";
    }

    /* 无注解 参数列表 表单传参，将表单参数一一列出，无需注解自动映射 */
    @PostMapping("/post2lose")
    public String postNoAnnotationLoseParameter(int id, String name, int age) {
        System.out.println("id: " + id + " name: " + name + " age: " + age);
        return "postNoAnnotation";
    }

    /* 注解 参数实体 url,body传参 */
    @PostMapping("/post/{type}")
    public String postNoAnnotationPathAndBody(@PathVariable String type, @RequestBody Member member) {
        System.out.println(type);
        System.out.println(member.toString());
        return "postNoAnnotation";
    }


    /* 无注解 对象参数 表单传参, 自动将表单参数转化为实体对象*/
    @PostMapping("/postObj")
    public String postNoAnnotationObj(Member member) {
        System.out.println(member);
        return "postNoAnnotationObj";
    }

    /* 注解 对象参数 json字符串传参 */
    /* 如果方法接收（contentType：application/json）类型的请求参数，使用@RequestBody标注
     * 将该参数内容转化为指定的对象参数
     */
    @PostMapping("/postObj2")
    public String postWithAnnotationObj(@RequestBody Member member) {
        System.out.println(member.toString());
        return "postWithAnnotationObj";
    }

    /* 上述方法也可以不加@RequestBody注解 通过HttpServletRequest对象的流对象接收 */
    @PostMapping("/postObjStr")
    public String postWithAnnotationStr(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String line;
        StringBuilder buffer = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        System.out.println(buffer.toString());
        return "postWithAnnotationStr";
    }

    /* 此处 同于Get请求 */
    @DeleteMapping("/del")
    public String del(int id, String name) {
        System.out.println("id: " + id + " name: " + name);
        return "delNoAnnotation";
    }

    @DeleteMapping("/del/{id}")
    public String del2(@PathVariable("id") int id, @RequestParam("name") String name) {
        System.out.println("id: " + id + " name: " + name);
        return "delWithAnnotation";
    }

    /**
     * PUT请求 表单参数，put请求本身不支持表单方式传参，要传参可以依托（contentType:application/json）json传参
     * 但是可以在web.xml中添加过滤器(filter)org.springframework.web.filter.HttpPutFormContentFilter
     * 来支持PUT请求表单参数
     */
    @PutMapping("/put")
    public String put(int id, String name) {
        System.out.println("id: " + id + " name: " + name);
        return "putNoAnnotation";
    }

    /* PUT请求 json参数 contentType：application/json 方式 */
    @PutMapping("/putObj")
    public String putObj(@RequestBody Member member) {
        System.out.println(member.toString());
        return "putWithAnnotation";
    }

    /* 上传文件，使用post/put请求，contentType：multipart/form-data */
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public void upload(HttpServletRequest request) throws IllegalStateException, IOException {
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = "E:/springUpload" + file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
    }


}
