package com.hiscat.sprigncloud.config;

import com.hiscat.springcloud.microservice.service.DepartmentService;
import com.hiscat.springcloud.microservice.service.DepartmentServiceFallbackFactory;
import feign.hystrix.FallbackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FallbackFactory<DepartmentService> fallbackFactory() {
        return new DepartmentServiceFallbackFactory();
    }
}
