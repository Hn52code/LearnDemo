package com.zhn.demo.webexample.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

public class FileTypeIntInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        // 判断是否为文件上传请求
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files = multipartRequest.getFileMap();
            Set<Map.Entry<String, MultipartFile>> entrySet = files.entrySet();
            for (Map.Entry<String, MultipartFile> entry : entrySet) {
                MultipartFile value = entry.getValue();
                String filename = value.getOriginalFilename();
                //判断是否为限制文件类型
                if (!checkFile(filename)) {
                    //限制文件类型，请求转发到原始请求页面，并携带错误提示信息
                    PrintWriter writer = response.getWriter();
                    writer.write(JSONObject.toJSONString(new WebResult(1,
                            "不允许这个文件上传：" + filename + "；不支持该后缀")));
                    writer.close();
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 判断是否为允许的上传文件类型,true表示允许
     */
    private boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "jpg,jpeg,gif,png,ico,bmp,xls,xlsx,csv,doc,docx,txt,pdf,ppt,pptx,zip,rar";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }
}
