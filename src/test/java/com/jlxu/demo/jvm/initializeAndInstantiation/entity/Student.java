package com.jlxu.demo.jvm.initializeAndInstantiation.entity;

import lombok.Data;

/**
 * 功能
 * 创建时间：2020年04月05日
 * 文件名称：Student
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 10:11
 *
 * @auther jlxu
 */
@Data
public class Student {

    private String name;

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }
}
