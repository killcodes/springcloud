package com.servicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by fly on 2018/7/26.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ServiceGatewayApplication {

    public static void main(String[] args){
        SpringApplication.run(ServiceGatewayApplication.class,args);
    }

    @Bean
    public MyFilter accessFilter(){
        return new MyFilter();
    }

}
