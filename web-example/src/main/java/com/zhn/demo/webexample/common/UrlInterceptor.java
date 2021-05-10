package com.zhn.demo.webexample.common;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("urlInterceptor")
public class UrlInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
//
//    public UrlInterceptor(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String uri = request.getRequestURI();
//        System.out.println("api 拦截 ----- " + uri);
//        if (uri.startsWith("login")) {
//            System.out.println("让行");
//            return true;
//        } else {
//            System.out.println("不让行");
//            // 校验 是否通过
//            return false;
//        }
        String token = request.getHeader("token");
        // String appKey = request.getHeader("appKey");
        if (token == null) {
            response.setStatus(403);
            response.getWriter().println("token is null");
            // response.getWriter().println("token or key is null");
            return false;
        }
        try {
            jwtUtil.parse(token);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            response.setStatus(403);
            response.getWriter().println("token is expire");
            return false;
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            response.setStatus(403);
            response.getWriter().println("unsupported Jwt");
            return false;
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            response.setStatus(403);
            response.getWriter().println("malformed Jwt");
            return false;
        } catch (SignatureException e) {
            e.printStackTrace();
            response.setStatus(403);
            response.getWriter().println("signature Jwt");
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.setStatus(403);
            response.getWriter().println("Illegal Jwt");
            return false;
        }
        return true;
    }
}
