package com.mimi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mimi.common.R;
import com.mimi.domain.Category;
import org.springframework.transaction.annotation.Transactional;


public interface CategoryService extends IService<Category> {
    void remove(Long id);

}
