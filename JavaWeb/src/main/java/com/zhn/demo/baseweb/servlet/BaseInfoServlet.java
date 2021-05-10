package com.zhn.demo.baseweb.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "BaseInfoServlet", value = "/base")
public class BaseInfoServlet extends HttpServlet {


    public BaseInfoServlet() {
        System.out.println("init");
    }

    @Override
    public void init() throws ServletException {

        /* 获取全局配置（web.xml） */
        ServletConfig config = super.getServletConfig();

        /* servlet容器 */
        ServletContext context = super.getServletContext();

        /* http会话 */
        HttpSession session;
        /* 请求 */
        ServletRequest request;

        System.out.println("servlet init...");

    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        System.out.println("远程IP：" + req.getRemoteAddr());
        System.out.println("远程端口号" + req.getRemotePort());
        System.out.println("访问地址：" + req.getContextPath());
        System.out.println("会话ID：" + req.getSession().getId());
        System.out.println("地址信息：" + req.getPathInfo());
        System.out.println("访问uri：" + req.getRequestURI());
        HttpSession session = req.getSession();

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
            System.out.println(cookie.getComment());
            System.out.println(cookie.getDomain());
            System.out.println(cookie.getMaxAge());
            System.out.println(cookie.getPath());
            System.out.println(cookie.getSecure());
            System.out.println(cookie.getVersion());
        }
//        String name = request.getParameter("name");
//        String ad = request.getParameter("name");
//        int age = Integer.parseInt(request.getParameter("age"));
//        response.setContentType("html/plain;charset=UTF-8");
        // response.getWriter().write("第一个servlet地址");
        resp.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
