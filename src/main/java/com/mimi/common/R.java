package com.mimi.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel("返回结果")
public class R<T> implements Serializable {
    @ApiModelProperty("编码")
    private Integer code;
    @ApiModelProperty("错误信息")
    private String msg;
    @ApiModelProperty("数据")
    private T data;
    @ApiModelProperty("动态数据")
    private Map map = new HashMap();

    public static <T> R<T> success(T obj){
        R<T> r = new R<>();
        r.code = 1;
        r.data = obj;
        return r;
    }

    public static <T> R<T> error(String msg){
        R<T> r = new R<>();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }



}
