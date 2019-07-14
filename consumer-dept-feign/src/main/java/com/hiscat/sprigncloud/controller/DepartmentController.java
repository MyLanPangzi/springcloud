package com.hiscat.sprigncloud.controller;

import com.hiscat.springcloud.microservice.entity.Department;
import com.hiscat.springcloud.microservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/dept/{id}")
    public Department get(@PathVariable Long id) {
        return departmentService.get(id);
    }

    @PostMapping("/dept/")
    public boolean save(@RequestBody Department department) {
        return this.departmentService.save(department);
    }

    @GetMapping("/depts/")
    public List<Department> departments() {
        return this.departmentService.list();
    }

}
