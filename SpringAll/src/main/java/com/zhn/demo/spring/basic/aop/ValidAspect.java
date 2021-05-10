package com.zhn.demo.spring.basic.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/* xml式切面  */
public class ValidAspect {

    public void beforeMethod(JoinPoint point) {
        System.out.println("开始啦。。");
    }

    public void afterMethod(JoinPoint point) {

    }

    public void afterRunningMethod(JoinPoint point, Object result) {

    }

    public void afterThrowingMethod(JoinPoint point, Throwable ex) {

    }

    public Object aroundMethod(ProceedingJoinPoint pjt) throws Exception {

        return null;
    }
}
