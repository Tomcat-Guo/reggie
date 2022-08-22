package com.mimi.common;


/**
 * 基于ThreadLocal封装的工具类，用于保存和读取当前登录用户id
 */
public class BaseContext{
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(long id){
        threadLocal.set(id);
    }

    public static Long getCurrentID() {
        return threadLocal.get();
    }
}
