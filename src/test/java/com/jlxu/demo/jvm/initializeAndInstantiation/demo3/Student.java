package com.jlxu.demo.jvm.initializeAndInstantiation.demo3;

import lombok.Data;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * 功能：使用(反)序列化机制创建对象
 * 创建时间：2020年04月05日
 * 文件名称：Student
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 10:19
 *
 * @auther jlxu
 */
@Data
public class Student implements Cloneable, Serializable {
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

        //写对象
        ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream
                        ("J:\\java\\idea_workspace\\springboot_demo\\src\\test\\resources\\student.bin"));
        output.writeObject(student);
        output.close();
        //读对象
        ObjectInputStream input = new ObjectInputStream(
                new FileInputStream
                        ("J:\\java\\idea_workspace\\springboot_demo\\src\\test\\resources\\student.bin"));
        Student stu = (Student) input.readObject();
        System.out.println(stu); //Student(name=jack)



    }
}
