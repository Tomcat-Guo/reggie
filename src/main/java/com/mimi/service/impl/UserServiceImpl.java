package com.mimi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mimi.dao.EmployeeDao;
import com.mimi.dao.UserDao;
import com.mimi.domain.Employee;
import com.mimi.domain.User;
import com.mimi.service.EmployeeService;
import com.mimi.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
