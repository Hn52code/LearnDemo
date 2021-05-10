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

@WebServlet(name = "RestFulServlet", value = "/rest")
public class RestFulServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求内容类型 返回null
        System.out.println(req.getContentType());
        /* 获取单个参数 */
        String id = req.getParameter("id");
        String method = req.getParameter("method");
        System.out.println("id：" + id);
        System.out.println("method：" + method);
        /* 请求参数的map形式 */
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.print("parameterName: " + entry.getKey() + "\nparameterValue: ");
            for (String str : entry.getValue()) {
                System.out.print(str);
            }
            System.out.println();
        }

        resp.setContentType("application/text;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(req.getMethod() + " success ...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求内容类型 针对 表单返回：application/x-www-form-urlencoded; charset=UTF-8
        System.out.println(req.getContentType());
        /* 获取单个参数 */
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String worker = req.getParameter("worker");
        System.out.println("name：" + name);
        System.out.println("age：" + age);
        System.out.println("worker：" + worker);
        /* 请求参数的map形式 */
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.print("parameterName: " + entry.getKey() + "\nparameterValue: ");
            for (String str : entry.getValue()) {
                System.out.print(str);
            }
            System.out.println();
        }

        resp.setContentType("application/text;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(req.getMethod() + " success ...");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求内容类型 针对 表单返回：application/x-www-form-urlencoded; charset=UTF-8
        System.out.println(req.getContentType());
        // put请求无法获取表单数据，也就是application/x-www-form-urlencoded;类型 在put请求中不会request不会去请求体中读取
        // 需采用流（流一般只能被读取一次）。
        BufferedReader reader = req.getReader();
        String parameter = "";
        String readLine;
        while ((readLine = reader.readLine()) != null) {
            parameter += readLine;
        }
        System.out.println("request body parameter: " + parameter);
        resp.setContentType("application/text;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(req.getMethod() + " success ...");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
