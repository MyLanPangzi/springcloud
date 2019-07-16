package com.hiscat.sprigncloud.microservice.controller;

import com.hiscat.springcloud.microservice.entity.Department;
import com.hiscat.springcloud.microservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/consumer/dept/{id}")
    public Department get(@PathVariable Long id) {
        return this.departmentService.get(id);
    }

}
