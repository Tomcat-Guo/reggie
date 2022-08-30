package com.mimi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mimi.common.BaseContext;
import com.mimi.dao.EmployeeDao;
import com.mimi.dao.OrderDao;
import com.mimi.domain.*;
import com.mimi.exception.CustomException;
import com.mimi.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Orders> implements OrderService {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 添加订单，和订单明细
     * @param orders
     */
    @Override
    @Transactional
    public void submit(Orders orders) {
        //用户ID
        Long userId = BaseContext.getCurrentID();

        //查询购物车
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(lqw);

        if (shoppingCartList == null || shoppingCartList.size()==0){
            throw new CustomException("购物车为空，不能下单");
        }


        //查询用户信息
        User user = userService.getById(userId);

        //查询地址信息
        AddressBook address = addressBookService.getById(orders.getAddressBookId());
        if (address == null){
            throw new CustomException("地址信息有误，无法下单");
        }

        long orderId = IdWorker.getId();

        AtomicInteger amount = new AtomicInteger(0);

        //
        List<OrderDetail> orderDetails = shoppingCartList.stream().map((item)->{
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrderId(orderId);
            orderDetail.setName(item.getName());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            orderDetail.setNumber(item.getNumber());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        //向订单插入一条数据
        orders.setId(orderId);
        orders.setNumber(String.valueOf(orderId));
        orders.setStatus(2);
        orders.setUserId(userId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setPayMethod(1);
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserName(user.getName());
        orders.setPhone(address.getPhone());
        orders.setAddress(address.getConsignee());
        orders.setConsignee(address.getConsignee());
        orders.setAddress((address.getProvinceName()==null?"":address.getProvinceName())+(address.getCityName()==null?"":address.getCityName())+
                (address.getDistrictName()==null?"":address.getDistrictName())+(address.getDetail()==null?"":address.getDetail()));

        this.save(orders);

        //向订单明细插入多条数据
        orderDetailService.saveBatch(orderDetails);

        //清空购物车
        shoppingCartService.remove(lqw);
    }
}
