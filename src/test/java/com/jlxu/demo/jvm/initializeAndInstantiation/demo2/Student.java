package com.jlxu.demo.jvm.initializeAndInstantiation.demo2;

import java.lang.reflect.Constructor;

/**
 * 功能：使用Clone方法创建对象
 * 创建时间：2020年04月05日
 * 文件名称：Student
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 10:19
 *
 * @auther jlxu
 */
public class Student implements Cloneable {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws Exception {
        Constructor<Student> constrStudent = Student.class.getConstructor(String.class);
        Student student = constrStudent.newInstance("jack");
        Student cStudent = (Student) student.clone();
    }
}
