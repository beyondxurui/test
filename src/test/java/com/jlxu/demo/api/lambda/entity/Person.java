package com.jlxu.demo.api.lambda.entity;

import lombok.Data;

/**
 * 功能
 * 创建时间：2020年03月29日
 * 文件名称：Person
 * 版本：1.0.0
 * 最后修改时间：2020/3/29 16:53
 *
 * @auther jlxu
 */
@Data
public class Person {

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
