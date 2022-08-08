package com.zhn.demo.spring.web.entity;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class Client {

    private String name;
    private String value;
    private ConcurrentHashMap<String, CountDownLatch> countMap = new ConcurrentHashMap<>();

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ConcurrentHashMap<String, CountDownLatch> getCountMap() {
        return countMap;
    }

    public void setCountMap(ConcurrentHashMap<String, CountDownLatch> countMap) {
        this.countMap = countMap;
    }
}
