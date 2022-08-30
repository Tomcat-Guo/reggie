package com.mimi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mimi.domain.DishFlavor;
import com.mimi.domain.Orders;
import org.springframework.core.annotation.Order;

public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
