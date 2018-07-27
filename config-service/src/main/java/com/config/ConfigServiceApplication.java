package com.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fly on 2018/7/26.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ConfigServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(ConfigServiceApplication.class,args);
    }


    public String helloError(){
        return "hello error";
    }

    @Value("${name}")
    private  String name;
    @Value("${age}")
    private  String age;
    @Value("${version}")
    private  String version="开发环境";

    @RequestMapping("/test")
    public String test(){
        return "你好，我是" + name + "," + age + "," + version;
    }

}
