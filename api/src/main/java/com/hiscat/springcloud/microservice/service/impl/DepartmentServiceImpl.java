package com.hiscat.springcloud.microservice.service.impl;

import com.hiscat.springcloud.microservice.entity.Department;
import com.hiscat.springcloud.microservice.service.DepartmentService;

import java.util.List;

//@Component
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public boolean save(Department department) {
        return false;
    }

    @Override
    public Department get(Long id) {
        float[] []f = new float[6][];

        return Department
                .builder()
                .name("provider is closed")
                .build();
    }

    @Override
    public List<Department> list() {
        return null;
    }
}
