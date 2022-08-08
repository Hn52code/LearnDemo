package com.zhn.demo.spring.basic.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnConfigContext {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Information createServer() {
        Information information = new Information();
        information.setName("hello world");
        return information;
    }

}
