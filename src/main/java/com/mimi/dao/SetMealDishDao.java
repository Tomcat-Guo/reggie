package com.mimi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mimi.domain.Setmeal;
import com.mimi.domain.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetMealDishDao extends BaseMapper<SetmealDish> {
}
