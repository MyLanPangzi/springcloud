package com.hiscat.springcloud.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class DepartmentProvider8000 {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentProvider8000.class, args);
    }
}
