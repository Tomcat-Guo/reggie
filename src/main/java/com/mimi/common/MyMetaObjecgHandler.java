package com.mimi.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjecgHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        Long id = BaseContext.getCurrentID();
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("createUser",id);
        metaObject.setValue("updateUser",id);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        long id = Thread.currentThread().getId();
//        log.info("线程ID：{}",id);
        Long id = BaseContext.getCurrentID();
//        log.info("操作者ID：{}",id);
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser",id);
    }
}
