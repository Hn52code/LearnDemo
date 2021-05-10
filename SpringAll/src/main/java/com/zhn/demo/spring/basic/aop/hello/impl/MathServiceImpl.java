package com.zhn.demo.spring.basic.aop.hello.impl;

import com.zhn.demo.spring.basic.aop.hello.MathService;
import org.springframework.stereotype.Service;

@Service
public class MathServiceImpl implements MathService {

    @Override
    public int add(int i, int j) {
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        return i - j;
    }

    @Override
    public int nul(int i, int j) {
        return  i * j;
    }

    @Override
    public int div(int i, int j) {
        return  i * j;
    }
}
