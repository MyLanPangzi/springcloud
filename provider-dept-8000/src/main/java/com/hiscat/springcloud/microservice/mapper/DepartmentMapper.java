package com.hiscat.springcloud.microservice.mapper;

import com.hiscat.springcloud.microservice.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    boolean insert(Department department);

    Department selectById(Long id);

    List<Department> selectAll();
}
