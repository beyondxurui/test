package com.jlxu.demo.designmode.builder;

/**
 * 功能 Setter方法
 * 创建时间：2020年03月12日
 * 文件名称：Person1
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 13:50
 *
 * @auther jlxu
 */
public class Person2 {

    public Person2(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private final String name;
    private final Integer age;
    private String address;

    public Person2(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
