package com.mimi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mimi.domain.Dish;
import com.mimi.domain.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorDao extends BaseMapper<DishFlavor> {
}
