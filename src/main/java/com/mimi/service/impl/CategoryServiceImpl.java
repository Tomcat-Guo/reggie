package com.mimi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mimi.common.R;
import com.mimi.dao.CategoryDao;
import com.mimi.domain.Category;
import com.mimi.domain.Dish;
import com.mimi.domain.Setmeal;
import com.mimi.exception.CustomException;
import com.mimi.service.CategoryService;
import com.mimi.service.DishService;
import com.mimi.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetMealService setMealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> lqw1 = new LambdaQueryWrapper<Dish>();
        lqw1.eq(Dish::getCategoryId,id);
        long count1 = dishService.count(lqw1);

        if (count1>0){
            throw new CustomException("当前分类下有关联菜品，无法删除");
        }

        LambdaQueryWrapper<Setmeal> lqw2 = new LambdaQueryWrapper<Setmeal>();
        lqw2.eq(Setmeal::getCategoryId,id);
        long count2 = setMealService.count(lqw2);

        if (count2>0){
            throw new CustomException("当前分类下有关联套餐，无法删除");
        }

        super.removeById(id);
    }
}
