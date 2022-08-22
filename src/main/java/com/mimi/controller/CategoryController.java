package com.mimi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mimi.common.R;
import com.mimi.domain.Category;
import com.mimi.domain.Dish;
import com.mimi.domain.Setmeal;
import com.mimi.service.CategoryService;
import com.mimi.service.DishService;
import com.mimi.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询分类
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> getCategoryPage(Integer page, Integer pageSize){
        //1. 构造分页构造器
        Page<Category> iPage = new Page<>(page, pageSize);
        //3. 构造条件构造器
        LambdaQueryWrapper<Category> lwq = new LambdaQueryWrapper();
        // 按更新时间降序
        lwq.orderByAsc(Category::getSort);
        //3 调用page方法
        categoryService.page(iPage,lwq);
        //4. 返回R
        return R.success(iPage);
    }

    /**
     * 新增菜品分类或套餐
     * @param category
     * @return
     */
    @PostMapping
    public R<String> addCategory(@RequestBody Category category){
        categoryService.save(category);
        return R.success("添加菜品成功");
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> deleteCategory(Long id){
        categoryService.remove(id);
        return R.success("成功删除分类信息");
    }

//    /**
//     * 编辑页面反查详情接口
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public R<Category> queryCategoryById(@PathVariable Long id){
//        Category category = categoryService.getById(id);
//        return R.success(category);
//    }

    /**
     * 修改分类接口
     * @param category
     * @return
     */
    @PutMapping
    public R<String> editCategory(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改分类成功");
    }

    @GetMapping("/list")
    // 获取菜品分类列表
    public R<List<Category>> getCategoryList(Integer type){

        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.eq(type != null, Category::getType,type);
        List<Category> list = categoryService.list(lqw);
        return R.success(list);
    }
}
