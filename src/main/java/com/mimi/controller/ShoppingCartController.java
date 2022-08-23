package com.mimi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mimi.common.BaseContext;
import com.mimi.common.R;
import com.mimi.domain.Dish;
import com.mimi.domain.ShoppingCart;
import com.mimi.service.DishService;
import com.mimi.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private DishService dishService;

    @Autowired

    @GetMapping("/list")
    public R<List<ShoppingCart>> list(HttpServletRequest request){
        Long userId = BaseContext.getCurrentID();

        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(userId != null,ShoppingCart::getUserId,userId);
        List<ShoppingCart> list = shoppingCartService.list(lqw);
        log.info(list.toString());
        return R.success(list);
    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, @RequestBody ShoppingCart shoppingCart){
        log.info(shoppingCart.toString());

//        LambdaQueryWrapper<ShoppingCart>

        Long userId = (Long) request.getSession().getAttribute("user");
        BaseContext.setCurrentId(userId);
        shoppingCart.setUserId(userId);

        String dishName = shoppingCart.getName();
        LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dish::getName,dishName);
        Dish dish = dishService.getOne(lqw);
        Long dishId = dish.getId();
        shoppingCart.setDishId(dishId);

        log.info(shoppingCart.toString());
        shoppingCartService.save(shoppingCart);
        log.info(shoppingCart.toString());
        return R.success("成功添加到购物车");
    }
}
