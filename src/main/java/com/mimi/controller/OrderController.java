package com.mimi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mimi.common.BaseContext;
import com.mimi.common.R;
import com.mimi.domain.OrderDetail;
import com.mimi.domain.Orders;
import com.mimi.domain.OrdersDto;
import com.mimi.service.OrderDetailService;
import com.mimi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/submit")
    public R<String> add(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("成功下单");
    }

    @GetMapping("/userPage")
    public R<Page> userPage(Integer page, Integer pageSize){
        //构建分页查询器
        Page<Orders> ordersPage = new Page<>(page,pageSize);
        Page<OrdersDto> ordersDtoPage = new Page<>();

        //构建条件查询器
        LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Orders::getUserId, BaseContext.getCurrentID());
        orderService.page(ordersPage, lqw);

        //复制page，排除records
        BeanUtils.copyProperties(ordersPage,ordersDtoPage,"records");

        //订单列表
        List<Orders> ordersList = ordersPage.getRecords();

        //遍历订单
        List<OrdersDto> ordersDtoList = ordersList.stream().map((item)->{
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(item,ordersDto);

            //查询订单明细列表
            Long orderId = item.getId();
            LambdaQueryWrapper<OrderDetail> lqw2 = new LambdaQueryWrapper<>();
            lqw2.eq(OrderDetail::getOrderId,orderId);
            List<OrderDetail> orderDetailList = orderDetailService.list(lqw2);

            ordersDto.setOrderDetails(orderDetailList);
            return ordersDto;
        }).collect(Collectors.toList());

        ordersDtoPage.setRecords(ordersDtoList);
        return R.success(ordersDtoPage);
    }

    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize){
        Page<Orders> ordersPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<>();
        orderService.page(ordersPage, lqw);
        return R.success(ordersPage);
    }
}
