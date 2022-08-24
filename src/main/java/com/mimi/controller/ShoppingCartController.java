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

    /**
     * 添加到购物车
     * @param request
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, @RequestBody ShoppingCart shoppingCart){

        shoppingCartService.add(request,shoppingCart);
        return R.success("成功添加到购物车");
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean(){
        shoppingCartService.remove(null);
        return R.success("成功清空");
    }

    @PostMapping("/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart){
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        ShoppingCart one = shoppingCartService.getOne(lqw);
        if (one.getNumber()==1){
            shoppingCartService.removeById(one);
            return R.success("数量为1，删除");
        }
        LambdaUpdateWrapper<ShoppingCart> luw = new LambdaUpdateWrapper<>();
        luw.set(one.getNumber()>1,ShoppingCart::getNumber,one.getNumber()-1);
        shoppingCartService.update(luw);
        return R.success("数量-1");
    }
}
