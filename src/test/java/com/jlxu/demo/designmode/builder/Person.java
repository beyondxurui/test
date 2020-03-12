package com.jlxu.demo.designmode.builder;

import lombok.Data;
import lombok.ToString;

/**
 * 功能：Builder模式的Person
 * 创建时间：2020年03月12日
 * 文件名称：Person
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 0:08
 *
 * @auther jlxu
 */
@ToString
public class Person {
    private final String name;
    private final Integer age;
    private final String address;//TODO：有构造就不报未初始化的错误了 （看提高篇，初始化又哪些方式）

    public Person(Builder builder) {
        this.name = builder.name;//同理这里的this指的时Person对象引用
        this.age = builder.age;
        this.address = builder.address;
    }

    @ToString
    static class Builder {
        private final String name;
        private final Integer age;
        private String address;

        public Builder(String name, Integer age) {
            this.name = name;   //TODO: 这里的this指的是Builder对象引用
            this.age = age;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        //不能少
        public Person builder() {
            //TODO:我们是对Person对象进行参数检查，而不是对Builder对象进行参数检查
            //(1)对Person对象进行参数检查
            Person person = new Person(this);
            if (!"boy".equals(person.name)) {
                throw new IllegalArgumentException("所注册用户必须为jlxu！");
            } else {
                return person;
            }
            //(2)对Builder对象进行参数检查
//            Person person = new Person(this);// 通过Builder构建所需Person对象，并且每次都产生新的Person对象
//            if (!"boy".equals(this.name)) {
//                throw new IllegalArgumentException("所注册用户必须为jlxu");
//            } else {
//                return person;
//            }
        }
    }

    //TODO:不行(因为.builder)  😂
//    public Person builder(String name, Integer age) {
//        return new Person.Builder(name, age).builder();
//    }
}
