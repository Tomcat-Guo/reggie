package com.mimi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mimi.common.R;
import com.mimi.domain.Category;
import com.mimi.domain.Dish;
import com.mimi.domain.DishDto;
import com.mimi.domain.DishFlavor;
import com.mimi.service.CategoryService;
import com.mimi.service.DishFlavorService;
import com.mimi.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询菜品
     * @param page
     * @param pageSize
     * @return
     */
    @Cacheable(value = "dishCache",key = "#page+'_'+#pageSize+'_'+#name")
    @GetMapping("/page")
    public R<Page> getDishPage(Integer page, Integer pageSize, String name){
        //1. 构造分页构造器
        Page<Dish> dishPage = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>(page,pageSize);
        //3. 构造条件构造器
        LambdaQueryWrapper<Dish> lwq = new LambdaQueryWrapper();
        // 按姓名模糊匹配
        lwq.like(StringUtils.isNotEmpty(name),Dish::getName,name);
        // 按更新时间降序
        lwq.orderByDesc(Dish::getUpdateTime);
        //3 调用page方法
        dishService.page(dishPage, lwq);

        //复制，排除records
        BeanUtils.copyProperties(dishPage,dishDtoPage,"records");

        List<Dish> records = dishPage.getRecords();

        List<DishDto> dishDtoPageRecords = records.stream().map((item)->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item,dishDto);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category!=null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(dishDtoPageRecords);

        //4. 返回R
        return R.success(dishDtoPage);
    }

    /**
     * 新增菜品
     * @param
     * @return
     */
    @CacheEvict(value = "dishCache",allEntries = true)
    @PostMapping
    public R<String> addDish(@RequestBody DishDto dishDto){
        dishService.saveWithFlavor(dishDto);
        String key = "dish_"+dishDto.getCategoryId()+"_"+dishDto.getStatus();
        redisTemplate.delete(key);
        return R.success("成功添加菜品");
    }

    /**
     * 删除菜品
     * @param id
     * @return
     */
    @CacheEvict(value = "dishCache",allEntries = true)
    @DeleteMapping
    public R<String> deleteDish(@RequestParam List<Long> id){
        dishService.deleteWithFlavor(id);
        redisTemplate.delete(redisTemplate.keys("dish_*"));
        return R.success("成功删除菜品");
    }

    /**
     * 修改菜品停售状态
     * @param status
     * @param
     * @return
     */
    @CacheEvict(value = "dishCache",allEntries = true)
    @PostMapping("/status/{status}")
    public R<String> changeStatus(@PathVariable Integer status, @RequestParam List<Long> id){
        dishService.changeBulkStatus(status,id);
        return R.success("成功修改菜品状态");
    }


    @GetMapping("/{id}")
    public R<DishDto> queryDishById(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    @CacheEvict(value = "dishCache",allEntries = true)
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        String key = "dish_"+dishDto.getCategoryId()+"_"+dishDto.getStatus();
        redisTemplate.delete(key);
        return R.success("成功修改菜品");
    }

    @GetMapping("/list")
    public R<List<DishDto>> getByCategoryId(Dish dish){
        List<DishDto> dishDtos = null;

        String key = "dish_"+dish.getCategoryId()+"_"+dish.getStatus();

        //查询redis缓存是否有数据
        dishDtos = (List<DishDto>) redisTemplate.opsForValue().get(key);
        //如果缓存有数据，直接返回
        if (dishDtos != null){
            return R.success(dishDtos);
        }

        LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();
        //菜品分类
        lqw.eq(dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId());
        //只显示启售状态
        lqw.eq(Dish::getStatus,1);
        lqw.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> dishes = dishService.list(lqw);
        dishDtos =dishes.stream().map((item)->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item,dishDto);
            Long dishId = item.getId();

            LambdaQueryWrapper<DishFlavor> lqw2 = new LambdaQueryWrapper<>();
            lqw2.eq(DishFlavor::getDishId,dishId);
            List<DishFlavor> flavors = dishFlavorService.list(lqw2);
            dishDto.setFlavors(flavors);
            return dishDto;
        }).collect(Collectors.toList());

        //缓存无数据，存到redis
        redisTemplate.opsForValue().set(key,dishDtos,60,TimeUnit.MINUTES);

        return R.success(dishDtos);
    }
}
