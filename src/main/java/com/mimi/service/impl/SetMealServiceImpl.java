package com.mimi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mimi.dao.SetMealDao;
import com.mimi.domain.Setmeal;
import com.mimi.domain.SetmealDish;
import com.mimi.domain.SetmealDto;
import com.mimi.exception.CustomException;
import com.mimi.service.SetMealDishService;
import com.mimi.service.SetMealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealDao, Setmeal> implements SetMealService {

    @Autowired
    private SetMealDishService setMealDishService;

    @Transactional
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        //写入setmeal表
        this.save(setmealDto);
        //写入setmeal_dish表
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item)->{
            //增加setmeal id
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setMealDishService.saveBatch(setmealDishes);
    }

    @Override
    public SetmealDto get(Long id) {
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal,setmealDto);

        LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SetmealDish::getSetmealId,id);
        List<SetmealDish> setmealDishes = setMealDishService.list(lqw);

        setmealDto.setSetmealDishes(setmealDishes);

        return setmealDto;
    }

    @Transactional
    @Override
    public void updateWithDish(SetmealDto setmealDto) {
        this.updateById(setmealDto);

        LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setMealDishService.remove(lqw);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setMealDishService.saveBatch(setmealDishes);
    }

    @Transactional
    @Override
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> lqw = new LambdaQueryWrapper<>();
        lqw.in(Setmeal::getId,ids).eq(Setmeal::getStatus,1);
        long count = this.count(lqw);
        if (count>0){
            throw new CustomException("套餐启售中，无法删除");
        }

        this.removeBatchByIds(ids);

        LambdaQueryWrapper<SetmealDish> lqw2 = new LambdaQueryWrapper<>();
        lqw2.in(SetmealDish::getSetmealId,ids);
        setMealDishService.remove(lqw2);
    }

    @Override
    @Transactional
    public void changeStatus(Integer status, List<Long> ids) {
        LambdaUpdateWrapper<Setmeal> luw = new LambdaUpdateWrapper<>();
        luw.in(Setmeal::getId,ids).set(Setmeal::getStatus,status);
        this.update(luw);
    }


}
