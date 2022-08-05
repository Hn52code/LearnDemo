package com.zhn.demo.webexample.controller;

import com.zhn.demo.webexample.common.WebResult;
import com.zhn.demo.webexample.pojo.People;
import com.zhn.demo.webexample.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Api("文件上传下载API")
@Validated
@RestController
@RequestMapping("/file")
public class FileController {

    private String rootFileName = "/upload";

    private String images = "/images";
    private String headImg = "/headimg";

    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传文件", notes = "这是一个上传文件的接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "文件名称", dataType = "file", required = true),
//    })
    public WebResult uploadFile(@RequestParam("file") MultipartFile file,
                                HttpServletRequest request) throws Exception {
        String path = request.getServletContext().getRealPath(rootFileName + images + headImg);
        if (file.isEmpty()) throw new FileNotFoundException("未选择文件");
        FileUtils.copyInputStreamToFile(file.getInputStream(),
                new File(path + "/" + file.getOriginalFilename()));
        return new WebResult(0, "上传成功");
    }


    @PostMapping("/uploads")
    @ApiOperation(value = "上传文件", notes = "这是一个上传多文件的接口")
    public WebResult uploadFiles(@RequestParam("files")
                                 @Valid MultipartFile[] files,
                                 HttpServletRequest request) throws Exception {
        if (files.length == 0) throw new FileNotFoundException("未选择文件");
        String path = request.getServletContext().getRealPath(rootFileName + images + headImg);
        for (MultipartFile file : files) {
            String uploadFilePath = path + "/" + file.getOriginalFilename();
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadFilePath));
        }
        return new WebResult(0, "上传成功");
    }


    @PostMapping(value = "/download", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@RequestParam("fileName") String fileName,
                                           HttpServletRequest request) throws Exception {
        String filePath = request.getServletContext().getRealPath(rootFileName + images + headImg) + "/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            /* 此处疑问？抛出的异常无法被捕捉，
             * 原因可能是消息转换器为文件类型，而返回的json，所以无法返回。猜想：在该方法类抛出异常，但是捕捉的方法
             * 所关联的返回值，以及背后的消息转换器是不同的，
             * 本接口中使用的是字节数组，而统一返回的webresult对象是默认用json对象返回的，
             *
             * 更新：我本人定义全局统一处理异常是是统一返回application/json的，
             * 本接口的返回值contextTye：application/octet-stream;charset=UTF-8 类型，必须有有与之配套的contextType
             * 的异常捕捉，此处使用携带HttpServletResponse参数的异常捕捉统一处理，原因是该参数可以返回
             * 任何contextType类型的响应数据，自然也包括octet-stream，但是也可以选择不抛出异常直接在本接口中
             * 使用HttpServletResponse 响应返回。
             */
//            PrintWriter writer = response.getWriter();
//            writer.write(JSONObject.toJSONString(new WebResult(1, "该文件不存在")));
//            writer.close();
//            return null;
            throw new FileNotFoundException("资源不存在");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attchement;filename=", fileName);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.OK);
    }


    @PostMapping(value = "/excel", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> excel() throws IOException {
        List<People> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            People people = new People();
            people.setId(i);
            people.setAddr("我来自派安--" + i);
            people.setAge(10 + i);
            people.setName("名字");
            if (i % 2 == 0)
                people.setSex("男");
            else
                people.setSex("女");
            list.add(people);
        }

        File file = new File("e:\\text.xlsx");
        if (!file.exists())
            file.createNewFile();

        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));

        new ExcelUtil<>(People.class).exportExcel(list, "test", 0, outputStream);

        outputStream.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attchement;filename=", file.getName());
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.OK);
    }
}
