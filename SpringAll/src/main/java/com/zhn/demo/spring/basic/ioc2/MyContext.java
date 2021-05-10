package com.zhn.demo.spring.basic.ioc2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyContext {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Server createServer() {
        Server server = new Server();
        server.setName("hello world");
        return server;
    }

}
