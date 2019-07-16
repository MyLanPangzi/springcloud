package com.hiscat.springcloud.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class DepartmentHystrixProvider8004 {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentHystrixProvider8004.class, args);
    }
}
