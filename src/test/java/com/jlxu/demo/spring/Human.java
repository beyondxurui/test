package com.jlxu.demo.spring;

import lombok.Data;

/**
 * 功能
 * 创建时间：2020年03月02日
 * 文件名称：Human
 * 版本：1.0.0
 * 最后修改时间：2020/3/2 22:16
 *
 * @auther jlxu
 */
@Data  //整的了解@Data注解？ ===> 使用@Data注解可以省去setter/getter以及toString、equals和hashCode方法     看constantTest中第一个日志输出
public class Human {
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }

    private String name;
    private Integer age;
}
