package com.hiscat.springcloud.microservice.service;

import com.hiscat.springcloud.microservice.entity.Department;
import com.hiscat.springcloud.microservice.service.impl.DepartmentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PROVIDER-DEPT", fallback = DepartmentServiceImpl.class)
public interface DepartmentService {
    @PostMapping("/dept/")
    boolean save(@RequestBody Department department);

    @GetMapping("/dept/{id}")
    Department get(@PathVariable Long id);

    @GetMapping("/depts/")
    List<Department> list();

}
