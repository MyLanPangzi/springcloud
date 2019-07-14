package com.hiscat.springcloud.microservice.service.impl;

import com.hiscat.springcloud.microservice.mapper.DepartmentMapper;
import com.hiscat.springcloud.microservice.service.DepartmentService;
import com.hiscat.springcloud.microservice.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Primary
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public boolean save(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public List<Department> list() {
        return this.departmentMapper.selectAll();
    }
}
