package com.mimi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mimi.dao.EmployeeDao;
import com.mimi.dao.ShoppingCartDao;
import com.mimi.domain.Employee;
import com.mimi.domain.ShoppingCart;
import com.mimi.service.EmployeeService;
import com.mimi.service.ShoppingCartService;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartDao, ShoppingCart> implements ShoppingCartService {
}
