package com.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fly on 2018/7/25.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class HelloServiceA {

    public static void main(String[] args){
        SpringApplication.run(HelloServiceA.class,args);
    }

    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String hostname;

    @RequestMapping("/hello")
    public String hello(){
        return "您好，我是" + hostname + ":" + port;
    }

}
