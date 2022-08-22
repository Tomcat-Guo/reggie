package com.mimi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mimi.dao.EmployeeDao;
import com.mimi.domain.Employee;
import com.mimi.service.EmployeeService;
import org.springframework.stereotype.Service;



@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements EmployeeService {
}
