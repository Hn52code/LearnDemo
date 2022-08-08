package com.zhn.demo.spring.web.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 白名单过滤器
 */
public class IPWhiteListFilter implements Filter {

    /* 编码 */
    private String encoding;
    /* 开启IP限制 */
    private boolean openIPLimit;
    /* IP白名单 */
    private List<String> whiteList;

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /* 获取编码 */
        String encoding = filterConfig.getInitParameter("encoding");
        if (encoding != null) {
            this.encoding = encoding;
        }
        /* 获取IP限制状态 */
        boolean isOpen = Boolean.parseBoolean(filterConfig.getInitParameter("openIPLimit"));
        this.openIPLimit = isOpen;
        if (isOpen) {
            initWhiteIpList();
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (encodingNotNull()) {
            req.setCharacterEncoding(encoding);
            resp.setCharacterEncoding(encoding);
        }
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (isOpenIPLimit()) {
            String address = request.getRemoteAddr();
            String uri = request.getRequestURI();
            StringBuffer url = request.getRequestURL();
            String host = request.getRemoteHost();
            int port = request.getRemotePort();
            if (containsIp(host)) {
                chain.doFilter(request, response);
                return;
            } else {
                response.getWriter().write("请通过白名单");
                response.getWriter().close();
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

    private void initWhiteIpList() {
        whiteList = new ArrayList<>();
        whiteList.add("0:0:0:0:0:0:0:1");
        whiteList.add("127.0.0.1");
        whiteList.add("192.168.0.63");
    }

    private boolean encodingNotNull() {
        return this.encoding != null;
    }

    private boolean isOpenIPLimit() {
        return this.openIPLimit;
    }

    private boolean containsIp(String targetIp) {
        for (String ip : whiteList) {
            if (ip.matches(targetIp))
                return true;
        }
        return false;
    }


}

