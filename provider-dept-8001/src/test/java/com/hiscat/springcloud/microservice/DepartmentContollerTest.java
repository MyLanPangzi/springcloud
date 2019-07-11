package com.hiscat.springcloud.microservice;

import com.hiscat.springcloud.microservice.entity.Department;
import com.hiscat.springcloud.microservice.service.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static com.alibaba.fastjson.JSON.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentContollerTest {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    WebApplicationContext app;

    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(this.app).build();
    }

    @Test
    @Sql("/sqls/test-get.sql")
    @Sql(value = "/sqls/clean-up.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeptGet() throws Exception {
        int id = 1;
        mockMvc.perform(get("/dept/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(equalTo(id)))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value(equalTo("测试")))
                .andExpect(jsonPath("$.dbSource").exists());
    }

    @Test
    public void testGet4xxWithEmptyId() throws Exception {
        mockMvc.perform(get("/dept/"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Sql(value = "/sqls/clean-up.sql")
    public void testGetEmptyWithNotExistsId() throws Exception {
        mockMvc.perform(get("/dept/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().bytes(new byte[]{}));
    }

    @Test
    public void testGet4xxWithErrorId() throws Exception{
        mockMvc.perform(get("/dept/{id}", "asdasd"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Sql(value = "/sqls/clean-up.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeptSave() throws Exception {
        Department build = Department.builder().name("测试部门").build();
        mockMvc.perform(post("/dept/").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(build)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(Boolean.toString(true)));
    }

    @Test
    @Sql("/sqls/test-list.sql")
    @Sql(value = "/sqls/clean-up.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeptList() throws Exception {
        Department d1 = Department.builder().id(1L).name("测试1").dbSource("test").build();
        Department d2 = Department.builder().id(2L).name("测试2").dbSource("test").build();
        Department d3 = Department.builder().id(3L).name("测试3").dbSource("test").build();
        mockMvc.perform(get("/depts/").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json(toJSONString(Arrays.asList(d1, d2, d3))));

    }

}
