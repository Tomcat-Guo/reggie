package com.mimi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mimi.dao.OrderDao;
import com.mimi.dao.OrderDetailDao;
import com.mimi.domain.OrderDetail;
import com.mimi.service.OrderDetailService;
import com.mimi.service.OrderService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetail> implements OrderDetailService {
}
