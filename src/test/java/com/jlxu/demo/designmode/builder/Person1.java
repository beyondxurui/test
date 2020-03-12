package com.jlxu.demo.designmode.builder;

import lombok.ToString;

/**
 * 功能 Setter方法
 * 创建时间：2020年03月12日
 * 文件名称：Person1
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 13:50
 *
 * @auther jlxu
 */
@ToString
public class Person1 {


    //    private final String name;
//    private final Integer age;
//    private final String address;  //No fields without setter were found  TODO:final不能用setter方法初始化
    //TODO：补充 final修饰的字段是不能使用setter方法（违背final方法的意愿，不可变对象）
    private String name;
    private Integer age;
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
