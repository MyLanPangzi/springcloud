package com.hiscat.springcloud.microservice.service;

import com.hiscat.springcloud.microservice.entity.Department;

import java.util.List;

public interface DepartmentService {
    boolean save(Department department);

    Department get(Long id);

    List<Department> list();
}
