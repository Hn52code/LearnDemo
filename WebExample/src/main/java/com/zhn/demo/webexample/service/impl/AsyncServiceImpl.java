package com.zhn.demo.webexample.service.impl;

import com.zhn.demo.webexample.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {
    
    @Async
    @Override
    public void print1() {
        System.out.println("print1----");
    }

    @Async
    @Override
    public void print2() {
        System.out.println("print2----");
    }

    @Async
    @Override
    public void print3() {
        System.out.println("print3----");
    }

    @Async
    @Override
    public void print4() {
        System.out.println("print4----");
    }

}
