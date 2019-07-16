package com.hiscat.sprigncloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.hiscat.springcloud.microservice")
//@ComponentScan({"com.hiscat.springcloud"})
@ComponentScans(@ComponentScan({"com.hiscat.springcloud"}))
@RestController
public class ConsumerHystrixApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixApp.class, args);
    }
}
