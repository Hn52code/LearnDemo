package com.zhn.demo.spring.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class TestController {

    private static ConcurrentHashMap<String, Client> clientMap = new ConcurrentHashMap<>();

    @PostConstruct
    void initMap() {
        clientMap.put("client1", new Client("client1"));
        clientMap.put("client2", new Client("client2"));
    }

    @GetMapping("/sendcmd")
    public String sendCmd(String name) {
        Client client = clientMap.get(name);
        CountDownLatch latch = new CountDownLatch(1);
        client.getCountMap().put(name, latch);
        try {
            latch.await(20, TimeUnit.SECONDS);
            return client.getValue();
        } catch (InterruptedException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/notify")
    public String notifyResult(String name, String value) {
        Client client = clientMap.get(name);
        client.setValue(value);
        client.getCountMap().get(name).countDown();
        return "";
    }

}


class Client {
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