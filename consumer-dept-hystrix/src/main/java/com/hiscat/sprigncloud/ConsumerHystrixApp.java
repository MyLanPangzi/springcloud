package com.hiscat.sprigncloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.hiscat.springcloud.microservice")
public class ConsumerHystrixApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixApp.class, args);
//        Set set = new HashSet();
//        set.add("aaa");
//        set.add("bbb");
//        set.add("aaa");
//        System.out.println(set.size());
    }
}
