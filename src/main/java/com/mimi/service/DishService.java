package com.mimi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mimi.domain.Dish;
import com.mimi.domain.DishDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
    void deleteWithFlavor(List<Long> ids);
    void updateWithFlavor(DishDto dishDto);
    DishDto getByIdWithFlavor(Long id);
    void changeBulkStatus(Integer status, List<Long> ids);
}
