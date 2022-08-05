package com.zhn.demo.webexample.controller;

import com.zhn.demo.webexample.common.BusinessException;
import com.zhn.demo.webexample.pojo.PersonDto;
import com.zhn.demo.webexample.pojo.Student;
import com.zhn.demo.webexample.pojo.UserDto;
import com.zhn.demo.webexample.service.StudentService;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

//当一些约束是直接出现在Controller层中的参数前时，只有将@Validated放在类上时，参数前的约束才会生效。
@Validated
@Api
@RestController
public class StudentController {

    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/hello")
    public String hello() {
        throw new RuntimeException("test 全局异常是否有效");
    }

    @GetMapping("/hello2")
    public String hello2() {
        throw new BusinessException(1, "test Business");
    }

    @GetMapping("/stu/{stuNo}")
    public String getStu(@PathVariable("stuNo") Integer stuNo) {

        return "";
    }

    @GetMapping("/stu")
    public String getStu2(
            Integer stuNo,
            @RequestParam(required = false)
            @Length(max = 6, message = "name的长度不能超过6")
                    String name) {

        return "";
    }

    @PostMapping("/stu/add")
    public void add(@RequestBody @Validated UserDto dto) {
        Student student = new Student();
        student.setStuName("zhouhainan");
        student.setAge(24);
        student.setSex(1);
        student.setBirthday(new Date());
        student.setAddress("这是来自湖南");
        studentService.add(student);
    }

    @PostMapping("/person/login")
    public void loginPerson(@RequestBody @Validated(PersonDto.Login.class) PersonDto dto) {

    }

    @PostMapping("/person/reg")
    public void registerPerson(@RequestBody @Validated(PersonDto.Register.class) PersonDto dto) {

    }

    @PutMapping("/stu/edit")
    public void put(Student student) {
    }

    @DeleteMapping("/stu/del/{stuNo}")
    public void del(int stuNo) {
    }

    @GetMapping("/sendemail")
    public void sendEmail() throws MessagingException {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol("smtp");
        javaMailSender.setHost("smtp.xx.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("xxxx@xx.com");
        javaMailSender.setPassword("xxxxxxxx");
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setDefaultEncoding("UTF-8");
        //ssl加密，需要加，否则无法运行

        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.timeout", "2000");
        //ssl加密，需要加，否则无法运行
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom("961848179@qq.com");
        helper.setTo("1430622667@qq.com");
        helper.setSubject("测试邮件主题");
        helper.setText("测试邮件内容");
        javaMailSender.send(mimeMessage);
    }

}
