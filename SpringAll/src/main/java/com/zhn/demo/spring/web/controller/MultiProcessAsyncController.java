package com.zhn.demo.spring.web.controller;

import com.zhn.demo.spring.web.entity.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/async")
public class MultiProcessAsyncController {

    private static final ConcurrentHashMap<String, Client> clientMap = new ConcurrentHashMap<>();

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
