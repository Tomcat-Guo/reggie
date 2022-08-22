package com.mimi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mimi.dao.DishDao;
import com.mimi.dao.DishFlavorDao;
import com.mimi.domain.Dish;
import com.mimi.domain.DishFlavor;
import com.mimi.service.DishFlavorService;
import com.mimi.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorDao, DishFlavor> implements DishFlavorService {
}
