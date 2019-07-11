package com.hiscat.springcloud.microservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department implements Serializable {
    private Long id;
    private String name;
    private String dbSource;
}
