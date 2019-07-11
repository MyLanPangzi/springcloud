package com.hiscat.sprigncloud.controller;

import com.hiscat.springcloud.microservice.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping()
public class DepartmentController {

    private static final String URL = "http://localhost:8001/";
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/dept/{id}")
    public Department get(@PathVariable Long id) {
        return this.restTemplate.getForObject(URL + "/dept/" + id, Department.class);
    }

    @PostMapping("/dept/")
    public boolean save(@RequestBody Department department) {
        return this.restTemplate.postForObject(URL + "/dept/", department, Boolean.class);
    }

    @GetMapping("/depts/")
    public List<Department> departments() {
        return this.restTemplate.getForObject(URL + "/depts/", List.class);
    }

}
