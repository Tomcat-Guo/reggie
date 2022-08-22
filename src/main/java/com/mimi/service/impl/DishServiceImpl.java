package com.mimi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.mimi.common.R;
import com.mimi.dao.DishDao;

import com.mimi.domain.Category;
import com.mimi.domain.Dish;

import com.mimi.domain.DishDto;
import com.mimi.domain.DishFlavor;
import com.mimi.exception.CustomException;
import com.mimi.service.CategoryService;
import com.mimi.service.DishFlavorService;
import com.mimi.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishDao, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //写入dish表
        this.save(dishDto);
        //写入dish_flavor表
        List<DishFlavor> flavors = dishDto.getFlavors();
        Long dishId = dishDto.getId();

//        for (DishFlavor flavor : flavors) {
//            flavor.setDishId(dishId);
//        }

        flavors = flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }

    @Override
    @Transactional
    public void deleteWithFlavor(List<Long> ids) {
        LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();
        lqw.in(Dish::getId,ids).eq(Dish::getStatus,1);
        long count = this.count(lqw);
        if (count>0){
            throw new CustomException("菜品启售中，无法删除");
        }
        this.removeBatchByIds(ids);

        LambdaQueryWrapper<DishFlavor> lqw2 = new LambdaQueryWrapper<>();
        lqw2.in(DishFlavor::getDishId,ids);
        dishFlavorService.remove(lqw2);
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        this.updateById(dishDto);

        LambdaQueryWrapper<DishFlavor> lqw = new LambdaQueryWrapper<>();
        lqw.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(lqw);

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        LambdaQueryWrapper<DishFlavor> lqw = new LambdaQueryWrapper<>();
        lqw.eq(DishFlavor::getDishId,id);
        List<DishFlavor> flavors = dishFlavorService.list(lqw);

        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Transactional
    @Override
    public void changeBulkStatus(Integer status, List<Long> ids) {
        LambdaUpdateWrapper<Dish> luw = new LambdaUpdateWrapper<>();
        luw.in(Dish::getId,ids).set(Dish::getStatus,status);
        this.update(luw);
    }

}
