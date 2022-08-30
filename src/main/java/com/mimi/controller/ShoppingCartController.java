package com.mimi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        Long userId = BaseContext.getCurrentID();

        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(userId != null,ShoppingCart::getUserId,userId);
        List<ShoppingCart> list = shoppingCartService.list(lqw);

        return R.success(list);
    }

    /**
     * 添加到购物车
     * @return
     */
//    @PostMapping("/add")
//    public R<String> add(HttpSession session, @RequestBody ShoppingCart shoppingCart){
//        shoppingCartService.add(session,shoppingCart);
//        return R.success("成功添加到购物车");
//    }
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        shoppingCart.setUserId(BaseContext.getCurrentID());
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(shoppingCart.getDishId()!=null,ShoppingCart::getDishId,shoppingCart.getDishId());
        lqw.eq(shoppingCart.getSetmealId()!=null,ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        lqw.eq(ShoppingCart::getUserId,BaseContext.getCurrentID());
        ShoppingCart one = shoppingCartService.getOne(lqw);
        if (one != null){
            one.setNumber(one.getNumber()+1);
            shoppingCartService.updateById(one);
            return R.success(one);
        }
        shoppingCartService.save(shoppingCart);
        return R.success(shoppingCart);
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean(){
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentID());
        shoppingCartService.remove(lambdaQueryWrapper);
        return R.success("成功清空");
    }

    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(shoppingCart.getDishId()!=null,ShoppingCart::getDishId,shoppingCart.getDishId());
        lqw.eq(shoppingCart.getSetmealId()!=null,ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        lqw.eq(ShoppingCart::getUserId,BaseContext.getCurrentID());
        ShoppingCart one = shoppingCartService.getOne(lqw);
        if (one.getNumber()>1){
            one.setNumber(one.getNumber()-1);
            shoppingCartService.updateById(one);
            return R.success(one);
        }
        shoppingCartService.removeById(one);
        return R.success(shoppingCart);
    }
}
