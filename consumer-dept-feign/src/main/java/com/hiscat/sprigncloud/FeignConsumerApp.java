package com.hiscat.sprigncloud;

import com.hiscat.sprigncloud.controller.DepartmentController;
import com.hiscat.springcloud.microservice.service.DepartmentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.hiscat.springcloud")
public class FeignConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApp.class, args);
    }
}
