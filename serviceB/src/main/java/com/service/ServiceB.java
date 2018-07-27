package com.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fly on 2018/7/25.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@RestController
public class ServiceB {

    public static void main(String[] args){
        SpringApplication.run(ServiceB.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @FeignClient(name = "serviceA")
    public interface HelloService{
        @RequestMapping(value = "/hello",method = RequestMethod.GET)
        String hello();
    }

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "helloError" )
    public String hello(){
        String result = restTemplate.getForObject("http://serviceA/hello",String.class);
        return result;
    }

    @RequestMapping("/helloFeign")
    public String helloFeign(){
        return helloService.hello();
    }

    public String helloError(){
        return "hello error";
    }

}
