package com.zhn.demo.baseweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "ParameterServlet", value = "/parameter")
public class ParameterServlet extends HttpServlet {

    /**
     * getParameter() 方法详解：https://blog.csdn.net/shuangmulin45/article/details/78083992
     * 它可以用于获取url中?后的键值对参数，或者表单(仅仅支持post，不支持put表单获取)数据(contentType:application/x-www-form-urlencoded)。
     * 此外其他参数将无法被getParameter()方法的底层获取。
     * 此处延伸 http请求post、get底层的方式。
     * post请求，一次完整的请求包括请求头，请求体（参数在其中），其中参数也可以在url中
     * get请求只有请求头（参数在url中）·
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求内容类型
        System.out.println(req.getContentType());
        /* 由于请求端为application/json，所以req.getParameter() 无法获取参数，*/
        /* 请求参数的map形式 */
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.print("parameterName: " + entry.getKey() + "\nparameterValue: ");
            for (String str : entry.getValue()) {
                System.out.print(str);
            }
            System.out.println();
        }
        BufferedReader reader = req.getReader();
        String parameter = "";
        String readLine;
        while ((readLine = reader.readLine()) != null) {
            parameter += readLine;
        }
        System.out.println("request body parameter: " + parameter);
        resp.setContentType("application/text;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(req.getMethod() + "json success ...");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
