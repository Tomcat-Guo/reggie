package com.mimi;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mimi.dao.EmployeeDao;
import com.mimi.domain.Employee;
import com.mimi.service.CategoryService;
import com.mimi.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReggieApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private CategoryService  categoryService;

    @Test
    void contextLoads() {
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<Employee>();
        lqw.eq(Employee::getUsername,"admin").eq(Employee::getPassword,"1");


    }

    @Test
    void delete(){
        categoryService.removeById(1560088069003882498L);
    }

}
