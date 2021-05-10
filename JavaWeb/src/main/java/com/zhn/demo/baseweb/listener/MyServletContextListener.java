package com.zhn.demo.baseweb.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        System.out.println(servletContext.getInitParameter("name1"));
        System.out.println(servletContext.getInitParameter("name2"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
