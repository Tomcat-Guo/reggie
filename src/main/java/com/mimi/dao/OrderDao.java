package com.mimi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mimi.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.Order;


@Mapper
public interface OrderDao extends BaseMapper<Order> {
}
