package com.jlxu.demo.response;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.http.HttpStatus;


/**
 * Created by zck on 2018/3/6.
 * 通用返回结果封装
 */
@Data
public class Result {
    // 返回状态
    private int status;
    // 状态描述
    private String desc;
    // 返回数据
    private JSON data;
    // 返回数据集
    private Object object;

    private String name;

    public Result(HttpStatus status) {
        this.status = status.value();
        this.desc = status.getReasonPhrase();
    }

    public Result(HttpStatus status, JSON jsonObject) {
        this.status = status.value();
        this.desc = status.getReasonPhrase();
        this.data = jsonObject;
    }

    public Result(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Result(int status, String desc, JSON data, String name) {
        this.status = status;
        this.desc = desc;
        this.data = data;
        this.name = name;
    }

    public Result(int status, String desc, JSON data) {
        this.status = status;
        this.desc = desc;
        this.data = data;
    }

    public Result(boolean isSuccess) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (isSuccess) {
            httpStatus = HttpStatus.OK;
        }
        this.status = httpStatus.value();
        this.desc = httpStatus.getReasonPhrase();
    }

    public Result(HttpStatus status, String desc) {
        this.status = status.value();
        this.desc = desc;
    }

    public Result(HttpStatus status, String desc, Object object) {
        this.status = status.value();
        this.desc = desc;
        this.object = object;
    }

    public Result(HttpStatus status, String desc, Object object, String name) {
        this.status = status.value();
        this.desc = desc;
        this.object = object;
        this.name = name;
    }

    public Result(HttpStatus status, Object object) {
        this.status = status.value();
        this.object = object;
    }
}
