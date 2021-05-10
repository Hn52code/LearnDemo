package com.zhn.demo.rabbitmq.channel.client.call;

import com.zhn.demo.rabbitmq.channel.client.outin.MqRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class OneCallHandler {

    private ConcurrentHashMap<String, ReentrantReadWriteLock> idReadWriteLockMap = new ConcurrentHashMap<>();
    @Autowired
    private MqRpcService mqRpcService;

    public String Reset(String id) {
        System.out.println(id +" _____ "+ Thread.currentThread());
        ReentrantReadWriteLock lock = idReadWriteLockMap.get(id);
        if (lock == null) {
            lock = new ReentrantReadWriteLock();
            idReadWriteLockMap.put(id, lock);
        }
        if (lock.isWriteLocked()) {
            return "已经占用中：" + id;
        }
        lock.writeLock().lock();
        String result = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("method", "put");
            map.put("uri", "implOne");
            Map<String, Object> paras = new HashMap<>();
            paras.put("key", id);
            map.put("paras", paras);
            result = mqRpcService.invokeMethod(map).toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
        return result;
    }

}
