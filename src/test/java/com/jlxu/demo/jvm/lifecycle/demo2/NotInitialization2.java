package com.jlxu.demo.jvm.lifecycle.demo2;

import com.jlxu.demo.jvm.lifecycle.demo2.entity.Person;

/**
 * 功能：通过数组定义来引用类，不会触发此类的初始化
 * 创建时间：2020年04月04日
 * 文件名称：NotInitialization2
 * 版本：1.0.0
 * 最后修改时间：2020/4/4 20:39
 *
 * @auther jlxu
 */
public class NotInitialization2 {
    public static void main(String[] args) {
        Person[] peopleArr = new Person[10];
        //没有日志输出，但是会触发另外一个类的触发，一个Person的一维数组，虚拟机自动生成的
    }
}
