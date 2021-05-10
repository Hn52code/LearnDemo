package com.zhn.demo.rabbitmq.channel.client;

import com.zhn.demo.rabbitmq.channel.client.call.OneCallHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProjectClientBoot {

    private static ConcurrentHashMap<String, ReentrantReadWriteLock> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("mq-channel-c.xml");
        /* 模仿多线程访问 */
//        for (int i = 0; i < 3; i++) {
//            new Thread(() -> {
//
//                MqRpcService bean = context.getBean(MqRpcService.class);
//                Map<String, Object> map = new HashMap<>();
//                map.put("method", "put");
//                map.put("uri", "implOne");
//                double v = Math.random() * 1000;
//                Map<String, Object> paras = new HashMap<>();
//                paras.put("key", v);
//                map.put("paras", paras);
//                ResponseContent callbackMessage = bean.invokeMethod(map);
//                System.out.println(callbackMessage.toString());
//            }).start();
//        }
//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                MqRpcService bean = context.getBean(MqRpcService.class);
//                Map<String, Object> map = new HashMap<>();
//                map.put("method", "put");
//                map.put("uri", "implTwo");
//                double v = Math.random() * 1000;
//                Map<String, Object> paras = new HashMap<>();
//                paras.put("key", v);
//                map.put("paras", paras);
//                ResponseContent callbackMessage = bean.invokeMethod(map);
//                System.out.println(callbackMessage.toString());
//            }).start();
//        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                OneCallHandler oneCallHandler = context.getBean(OneCallHandler.class);
                String str = oneCallHandler.Reset("1234");
                System.out.println(str);
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                OneCallHandler oneCallHandler = context.getBean(OneCallHandler.class);
                String str = oneCallHandler.Reset("1646");
                System.out.println(str);
            }).start();
        }
    }

}
