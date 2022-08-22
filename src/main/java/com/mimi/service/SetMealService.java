package com.mimi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mimi.domain.Dish;
import com.mimi.domain.Setmeal;
import com.mimi.domain.SetmealDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SetMealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    SetmealDto get(Long id);

    void updateWithDish(SetmealDto setmealDto);

    void removeWithDish(List<Long> ids);

    void changeStatus(Integer status, List<Long> ids);
}
