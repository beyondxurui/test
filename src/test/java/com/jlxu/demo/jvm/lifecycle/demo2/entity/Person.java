package com.jlxu.demo.jvm.lifecycle.demo2.entity;

/**
 * 功能
 * 创建时间：2020年04月04日
 * 文件名称：Person
 * 版本：1.0.0
 * 最后修改时间：2020/4/4 20:40
 *
 * @auther jlxu
 */
public class Person {
    static {
        System.out.println("Person");
    }
}
