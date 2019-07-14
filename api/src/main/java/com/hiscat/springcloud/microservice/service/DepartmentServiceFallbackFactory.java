package com.hiscat.springcloud.microservice.service;

import com.hiscat.springcloud.microservice.entity.Department;
import feign.hystrix.FallbackFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentServiceFallbackFactory implements FallbackFactory<DepartmentService>, PriorityOrdered {

    @Override
    public DepartmentService create(Throwable cause) {
        return new DepartmentService() {
            @Override
            public boolean save(Department department) {
                return false;
            }

            @Override
            public Department get(Long id) {
                return Department
                        .builder()
                        .name("provider is closed. wait a moment...")
                        .build();
            }

            @Override
            public List<Department> list() {
                return null;
            }
        };
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
