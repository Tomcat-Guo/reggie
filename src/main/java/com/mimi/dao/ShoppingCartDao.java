package com.mimi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mimi.domain.Employee;
import com.mimi.domain.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ShoppingCartDao extends BaseMapper<ShoppingCart> {
}
