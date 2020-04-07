package com.jlxu.demo.jvm.initializeAndInstantiation.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 功能：使用Constructor类的newInstance方法(反射机制)
 * 创建时间：2020年04月05日
 * 文件名称：Student
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 10:19
 *
 * @auther jlxu
 */
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws Exception {
        Constructor<Student> constrStudent = Student.class.getConstructor(String.class);
        Student student = constrStudent.newInstance("jack");
    }
}
