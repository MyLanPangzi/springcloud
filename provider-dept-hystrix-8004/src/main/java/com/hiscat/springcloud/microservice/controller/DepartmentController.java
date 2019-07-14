package com.hiscat.springcloud.microservice.controller;

import com.hiscat.springcloud.microservice.entity.Department;
import com.hiscat.springcloud.microservice.service.DepartmentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/dept/{id}")
    @HystrixCommand(fallbackMethod = "getError")
    public Department get(@PathVariable Long id) {
        Department department = departmentService.get(id);
        if (department == null) {
            throw new NullPointerException();
        }
        return department;
    }

    public Department getError(Long id) {
        return Department
                .builder()
                .name(id + " is not exists")
                .build();
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
