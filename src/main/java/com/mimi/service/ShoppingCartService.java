package com.mimi.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.mimi.domain.Employee;
import com.mimi.domain.ShoppingCart;

import javax.servlet.http.HttpServletRequest;


public interface ShoppingCartService extends IService<ShoppingCart> {
    void add(HttpServletRequest request, ShoppingCart shoppingCart);

}
