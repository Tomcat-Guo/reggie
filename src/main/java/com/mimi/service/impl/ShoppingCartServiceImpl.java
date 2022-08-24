package com.mimi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mimi.common.BaseContext;
import com.mimi.common.R;
import com.mimi.dao.EmployeeDao;
import com.mimi.dao.ShoppingCartDao;
import com.mimi.domain.Dish;
import com.mimi.domain.DishFlavor;
import com.mimi.domain.Employee;
import com.mimi.domain.ShoppingCart;
import com.mimi.service.DishFlavorService;
import com.mimi.service.DishService;
import com.mimi.service.EmployeeService;
import com.mimi.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartDao, ShoppingCart> implements ShoppingCartService {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Override
    public void add(HttpServletRequest request, ShoppingCart shoppingCart) {
        //添加userID
        Long userId = (Long) request.getSession().getAttribute("user");
        BaseContext.setCurrentId(userId);
        shoppingCart.setUserId(userId);

        //根据名字添加dishID
//        String dishName = shoppingCart.getName();
//        LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(Dish::getName,dishName);
//        Dish dish = dishService.getOne(lqw);
//        Long dishId = dish.getId();
//        shoppingCart.setDishId(dishId);

        //根据dish_id获取dish_flavor
//        LambdaQueryWrapper<DishFlavor> lqw3 = new LambdaQueryWrapper<>();
//        lqw3.eq(DishFlavor::getDishId,dishId);
//        DishFlavor dishFlavor = dishFlavorService.getOne(lqw3);
//        shoppingCart.setDishFlavor(dishFlavor.getValue());

        //判断菜品是否已在购物车，是的话数量+1
        LambdaQueryWrapper<ShoppingCart> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(ShoppingCart::getName,shoppingCart.getName());
        ShoppingCart one = this.getOne(lqw2);
        if (one != null){
            LambdaUpdateWrapper<ShoppingCart> luw = new LambdaUpdateWrapper<>();
            luw.eq(ShoppingCart::getName,shoppingCart.getName()).set(ShoppingCart::getNumber,one.getNumber()+1);
            this.update(luw);
            return;
        }
        //不是的话将菜品加到表中
        this.save(shoppingCart);
    }
}
