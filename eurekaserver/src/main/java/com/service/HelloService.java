package com.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by fly on 2018/7/25.
 */
@SpringBootApplication
@EnableEurekaServer
public class HelloService {

    public static void main(String[] args){
        SpringApplication.run(HelloService.class,args);
    }

}
